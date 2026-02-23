package app.domain.ports;

public interface UserPort {

    public boolean existsByDocument (String cedula);
    public boolean existsByUserName (String userName);
    public void save(app.domain.models.User user);

}
