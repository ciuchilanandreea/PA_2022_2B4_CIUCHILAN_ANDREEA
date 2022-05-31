import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {
    private static int startPackage;

    public static void main(String[] args) {

        System.out.print("Select a file: ");
        Scanner input = new Scanner(System.in).useDelimiter("\n");
        String inputFile = input.next();
        String[] paths = {"D:\\git\\PA_2022_2B4_CIUCHILAN_ANDREEA\\Lab13\\Compulsory\\out\\production\\Compulsory\\app",
                "D:\\git\\PA_2022_2B4_CIUCHILAN_ANDREEA\\Lab3",
                "D:\\git\\PA_2022_2B4_CIUCHILAN_ANDREEA\\Lab10\\Server",
                "D:\\git\\PA_2022_2B4_CIUCHILAN_ANDREEA\\Lab2\\out"};
        String pathToClasses = paths[3];
        File path = new File(pathToClasses);
        boolean fileFound = false;

        for (File file : Objects.requireNonNull(path.listFiles()))
            if (inputFile.equals(file.getName())) {
                fileFound = true;
                if (inputFile.contains(".class")) {
                    int len = inputFile.length();
                    findClass(pathToClasses, inputFile, len, file);
                } else if(inputFile.contains(".jar")){
                    System.out.println("Jar "+file.getName()+" found.");
                    try {
                        displayJARContents(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (!inputFile.contains(".") || inputFile.charAt(0) == '.') {
                    System.out.println("Directory " + file.getName() + " found.");
                    try {
                        displayDirectoryContents(file);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        if (!fileFound)
            System.out.println("File not found.");


    }

    public static void displayDirectoryContents(File dir) throws IOException {

        File[] files = dir.listFiles();
        if(files==null)
            return;
        for (File file : files) {
            if (file.isDirectory()) {
                System.out.println("directory: " + file.getName());
                displayDirectoryContents(file);
            } else {
                System.out.println("file: " + file.getName());
                if (file.getName().endsWith(".class")) {
                    int len = file.getName().length();
                    findClass(file.getAbsolutePath(), file.getName(), len, file);
                }
                if(file.getName().endsWith(".jar")){
                    int len=file.getName().length();
                    displayJARContents(file);
                }
            }
        }
    }
    public static void displayJARContents(File dir) throws IOException {
        ZipInputStream zip = new ZipInputStream(new FileInputStream(dir));
        for (ZipEntry entry = zip.getNextEntry(); entry != null; entry = zip.getNextEntry()) {
            if(entry.isDirectory()){
                System.out.println("directory: " + entry.getName());
                File file=new File(entry.toString());
                displayDirectoryContents(file);
            }
            else{
                if(entry.getName().endsWith(".class")) {
                    System.out.println("file: " + entry.getName() + " found");
                    int len = entry.getName().length();
                }
                else if(entry.getName().endsWith(".jar")) {
                    System.out.println("Jar " + entry.getName() + " found.");
                    displayJARContents(new File(entry.getName()));
                }


            }

                System.out.println(entry);
        }
    }


    public static void findClass(String pathToClasses, String inputFile, int len, File file) {
        startPackage = 0;
        try {
            loadClass(pathToClasses, inputFile.substring(0, len - 6));
            //System.out.println("Class "+inputFile+" found in folder "+pathToClasses.substring(0, startPackage - 1));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException | NoClassDefFoundError e) {
            //class is inside a package
            String className = getClassWithPackage(file, inputFile, len); //get the class binary name
            try {
                loadClass(pathToClasses.substring(0, startPackage - 1), className);
                System.out.println("Class " + inputFile + " found in folder " + pathToClasses.substring(0, startPackage - 1));
            } catch (ClassNotFoundException | MalformedURLException ex) {
                ex.printStackTrace();
            } catch (NoClassDefFoundError exception) {
                System.out.println("Class " + className + " not found in folder " + pathToClasses.substring(0, startPackage - 1) + " : unknown package.\n");
            }

        }
    }

    public static void loadClass(String searchPath, String className) throws ClassNotFoundException, MalformedURLException {
        File path = new File(searchPath);
        URL[] url = new URL[]{path.toURI().toURL()};
        URLClassLoader classloader = new URLClassLoader(url);
        Class clazz = classloader.loadClass(className);
        System.out.println("Class loaded.");
        getInfo(clazz);
    }

    public static String getClassWithPackage(File file, String inputFile, int len) {
        String className;
        startPackage = 0;
        int endPackage = file.getAbsolutePath().indexOf(inputFile) - 1;
        for (int i = endPackage - 1; i >= 0; i--)
            if (file.getAbsolutePath().charAt(i) == '\\') {
                startPackage = i + 1;
                break;
            }
        className = file.getAbsolutePath().substring(startPackage, endPackage) + "." + inputFile.substring(0, len - 6);
        return className;
    }

    public static void getInfo(Class loadedClass) {
        String packageClass = loadedClass.getPackageName();
        if (packageClass.isEmpty())
            packageClass = "not inside a package";
        System.out.println("Package Name: " + packageClass);
        System.out.println("Methods: " + Arrays.toString(loadedClass.getDeclaredMethods()));
        System.out.println("Constructors: " + Arrays.toString(loadedClass.getConstructors()));
        System.out.println("Declared Fields: " + Arrays.toString(loadedClass.getDeclaredFields()));
        System.out.println("Interfaces implemented: " + Arrays.toString(loadedClass.getGenericInterfaces()));
        System.out.println("Superclass extended: " + loadedClass.getSuperclass()+"\n\n");

        //get methods

        int sampleSize=30;
        int failedTests=0,passedTests=0,passedMethod=0,failedMethod=0;
        Object obj = null;

        try {
            obj = loadedClass.getDeclaredConstructor().newInstance();
            System.out.println("Statistics:");
            //get method statistics
            for (Method m : loadedClass.getMethods())
                if (m.isAnnotationPresent(Test.class)) {
                    for (int i = 0; i < sampleSize; i++)
                        if (isTestMethodPassed(m, obj))
                            passedMethod++;
                        else
                            failedMethod++;
                    System.out.println("Method "+m.getName()+" passed "+passedMethod/sampleSize+" times" +
                            " and failed "+failedMethod/sampleSize+" times on average for a sample size of "+sampleSize);
                }

            //get overall statistics
            for(int i=0;i<sampleSize;i++){
                List<Integer> foundResults=getMethods(loadedClass,obj);
                passedTests+=foundResults.get(0);
                failedTests+=foundResults.get(1);
            }

            System.out.println("\nThe methods from class "+loadedClass.getName()+" passed "+passedTests/sampleSize+" tests" +
                    " and failed "+failedTests/sampleSize+" tests on average for a sample size of "+sampleSize+"\n\n");
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            System.out.println("Unable to create instance of class");
        }catch(NoSuchMethodException e){
            System.out.println("Cannot create instance of class "+loadedClass.getName()+": no public constructor found.\n\n");
        }

    }
    public static List<Integer> getMethods(Class loadedClass,Object obj){
        List<Integer>results= new ArrayList<>();
        int failed = 0, passed = 0;
        for (Method m : loadedClass.getMethods())
            if (m.isAnnotationPresent(Test.class)) {
                if (isTestMethodPassed(m, obj))
                    passed++;
                else
                    failed++;
            }



        results.add(passed);
        results.add(failed);
        return results;
    }
    public static boolean isTestMethodPassed(Method m,Object obj){
        if (m.isAnnotationPresent(Test.class)) {
            if (Modifier.isStatic(m.getModifiers())) {
                if (m.getParameterCount() == 0) {
                    try {
                        m.invoke(null);
                        return true;
                    } catch (Throwable ex) {
                        //System.out.printf("Test %s failed: %s %n", m, ex.getCause());
                        return false;
                    }
                } else { //function has multiple parameters
                    List<Object> parameterList = generateValues(m);
                    try {
                        m.invoke(null, parameterList.toArray());
                        return true;
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        //System.out.printf("Test %s failed: %s %n", m, e.getCause());
                        return false;
                    }


                }
            } else { //method is not static
                if (m.getParameterCount() == 0) {
                    try {
                        m.invoke(obj);
                        return true;
                    } catch (Throwable ex) {
                        //System.out.printf("Test %s failed: %s %n", m, ex.getCause());
                        return false;
                    }
                }
                else { //function has multiple parameters
                    List<Object> parameterList = generateValues(m);
                    try {
                        m.invoke(obj, parameterList.toArray());
                        return true;
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        //System.out.printf("Test %s failed: %s %n", m, e.getCause());
                        return false;
                    }


                }

            }
        }
        return false;
    }

    public static List<Object> generateValues(Method m) {
        List<Object> parameterList = new ArrayList<>();
        Class<?>[] parameterTypes = m.getParameterTypes();
        for (int i = 0; i < parameterTypes.length; i++) {
            assert false;
            if (parameterTypes[i].isPrimitive()) {
                if (parameterTypes[i] == int.class) {
                    parameterList.add((int) (Math.random() * 10000));

                } else if (parameterTypes[i] == double.class) {
                    parameterList.add(Math.random() * 10000);
                }
            } else if (parameterTypes[i] == String.class) {
                StringBuilder y = new StringBuilder();
                int length = (int) (Math.random() * 100);
                for (int j = 0; j < length; j++) {
                    int character = (int) (Math.random() * 127);
                    y.append((char) (character + '0'));

                }
                String yNew = y.toString();
                parameterList.add(yNew);
            }


        }
        return parameterList;
    }

}


//cat mai multe lab combinate, bd, interfata
