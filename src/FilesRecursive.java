import java.io.File;

public class FilesRecursive {

    public static void main(String[] args) {
        String pathRoot = "D:\\Working\\Ultimate_Project\\basejava";
        File rootCatalog = new File(pathRoot);
        listFilesRecursive(rootCatalog);
    }

    private static void listFilesRecursive(File directory) {
         File[] listFiles = directory.listFiles();

         if (listFiles != null) {
            for (File item : listFiles) {
                System.out.println(item.getName());
                if (item.isDirectory()) {
                    listFilesRecursive(item);
                }

            }
        }

    }
}
