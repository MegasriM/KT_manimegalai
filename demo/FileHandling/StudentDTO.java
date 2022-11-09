package FileHandling;

public class StudentDTO
{
    String name;
//    int age;
    String stream;
    String height;

    public StudentDTO()
    {

    }

    public StudentDTO(String Name, String stream, String Height) {
        this.name = Name;
       // this.age = Age;
        this.stream = stream;
        this.height = Height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public int getAge() {
//        return age;
//    }
//
//    public void setAge(int age) {
//        this.age = age;
//    }


    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "StudentDTO {" +
                "name = '" + name + '\'' +
                ", stream = " + stream +
                ", height = '" + height + '\'' +
                '}';
    }
}