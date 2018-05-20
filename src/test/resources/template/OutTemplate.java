class InTemplate {
    public void method(java.util.Collection col) {
        if ((col.size()) > 10)
            throw new java.lang.IndexOutOfBoundsException();

    }
}