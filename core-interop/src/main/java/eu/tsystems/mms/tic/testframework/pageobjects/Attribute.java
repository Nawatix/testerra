package eu.tsystems.mms.tic.testframework.pageobjects;

public enum Attribute {
    CLASS("class"),
    ID("id"),
    NAME("name"),
    HREF("href"),
    TITLE("title"),
    VALUE("value")
    ;

    private final String attrib;

    Attribute(final String attrib) {
        this.attrib = attrib;
    }

    @Override
    public String toString() {
        return attrib;
    }
}
