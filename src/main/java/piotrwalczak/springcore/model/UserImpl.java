package piotrwalczak.springcore.model;

public class UserImpl implements User {

    private long id;
    private String name;
    private String email;

    protected static int ID_COUNT = 1;

    public UserImpl() {
        this.id = ++ID_COUNT;
    }

    public UserImpl(String name, String email) {
        this();
        this.name = name;
        this.email = email;
    }

    @Override
    public long getId() {
        return id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String email) {
        this.email = email;
    }
}
