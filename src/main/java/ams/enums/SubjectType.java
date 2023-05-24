package ams.enums;

public enum SubjectType {
    Organizational("Organizational Overview & Culture"),
    Company_Process("Company Process"),
    Standard_Process("Standard Process"),
    IT_Technical("IT Technical"),
    Non_IT_Technical("Non IT Technical"),
    Foreign_Language("Foreign Language"),
    Soft_Skills("Soft Skills"),
    Management("Management"),
    ;


    private final String name;
    SubjectType(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
