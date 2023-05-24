package ams.enums;

public enum SubSubjectType {
    Cloud("Cloud"),
    Big_Data("Big Data"),
    CAD ("CAD"),
    CAE ("CAE"),
    SAP ("SAP"),
    IT_General  ("IT General"),
    Test    ("Test"),
    Others  ("Others");

    private final String name;
    SubSubjectType(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
