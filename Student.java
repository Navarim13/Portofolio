public class Student {
    private String nim;
    private String name;
    private String major;

    public Student(String nim, String name, String major) {
        this.nim = nim;
        this.name = name;
        this.major = major;
    }

    public String getNim() {
        return nim;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void printInfo() {
        System.out.println("NIM   : " + nim);
        System.out.println("Nama  : " + name);
        System.out.println("Jurusan: " + major);
        System.out.println("-------------------------");
    }
}
