package com.urize.webapp.storage.serializable;

import com.urize.webapp.model.*;

import java.io.*;
import java.time.YearMonth;
import java.util.*;

public class DataSerializer implements SerializableInterface {
    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());

            if(r.getContacts() != null){
                Map<ContactsType, String> contacts = r.getContacts();
                writeCollection(dos, contacts.entrySet(), entry -> {
                    dos.writeUTF(entry.getKey().name());
                    dos.writeUTF(entry.getValue());
                });
            }

            if (r.getSections() != null) {
                writeCollection(dos, r.getSections().entrySet(), entry -> {
                    SectionType type = entry.getKey();
                    AbstractSection section = entry.getValue();
                    dos.writeUTF(type.name());
                    switch (type) {
                        case PERSONAL, OBJECTIVE:
                            dos.writeUTF(((TextSection) section).getSection());
                            break;
                        case ACHIEVEMENT, QUALIFICATIONS:
                            writeCollection(dos, ((ListSection) section).getList(), dos::writeUTF);
                            break;
                        case EXPERIENCE, EDUCATION:
                            writeCollection(dos, ((CompanySection) section).getList(), org -> {
                                dos.writeUTF(org.getLink().getName());
                                dos.writeUTF(org.getLink().getUrl());
                                writeCollection(dos, org.getPeriods(), position -> {
                                    writeYearMonth(dos, position.getStartDate());
                                    writeYearMonth(dos, position.getEndDate());
                                    dos.writeUTF(position.getTitle());
                                    dos.writeUTF(position.getDescription());
                                });
                            });
                            break;
                    }
                });
            }
        }

    }

    private void writeYearMonth(DataOutputStream dos, YearMonth ld) throws IOException {
        dos.writeInt(ld.getYear());
        dos.writeInt(ld.getMonth().getValue());
    }

    private YearMonth readYearMonth(DataInputStream dis) throws IOException {
        return YearMonth.of(dis.readInt(), dis.readInt());
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readItems(dis, () -> resume.addContacts(ContactsType.valueOf(dis.readUTF()), dis.readUTF()));
            readItems(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.addSections(sectionType, readSection(dis, sectionType));
            });
            return resume;
        }
    }

    private AbstractSection readSection(DataInputStream dis, SectionType sectionType) throws IOException {
        return switch (sectionType) {
            case PERSONAL, OBJECTIVE -> new TextSection(dis.readUTF());
            case ACHIEVEMENT, QUALIFICATIONS -> new ListSection(readList(dis, dis::readUTF));
            case EXPERIENCE, EDUCATION -> new CompanySection(
                    readList(dis, () -> new Company(
                            new Link(dis.readUTF(), dis.readUTF()),
                            readList(dis, () -> new Company.Period(
                                    readYearMonth(dis), readYearMonth(dis), dis.readUTF(), dis.readUTF()
                            ))
                    )));
            default -> throw new IllegalStateException();
        };
    }

    private <T> List<T> readList(DataInputStream dis, ElementReader<T> reader) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(reader.read());
        }
        return list;
    }

    private interface ElementProcessor {
        void process() throws IOException;
    }

    private interface ElementReader<T> {
        T read() throws IOException;
    }

    private interface ElementWriter<T> {
        void write(T t) throws IOException;
    }

    private void readItems(DataInputStream dis, ElementProcessor processor) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            processor.process();
        }
    }

    private <T> void writeCollection(DataOutputStream dos, Collection<T> collection, ElementWriter<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T item : collection) {
            writer.write(item);
        }
    }
}
