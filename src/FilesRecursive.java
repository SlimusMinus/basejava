import java.io.File;

public class FilesRecursive {

    public static void main(String[] args) {
        String pathRoot = "D:\\Working\\Ultimate_Project\\basejava";
        File rootCatalog = new File(pathRoot);
        listFilesRecursive(rootCatalog);
    }

    private static void listFilesRecursive(File directory){
        File[] files = directory.listFiles();
        if(files != null){
            for(var item : files){
                if(item.isDirectory()){
                    System.out.println("Directory " + item.getName());
                    listFilesRecursive(item);
                }
                else{
                    System.out.println("  File " + item.getName());
                }
            }
        }

    }
}
