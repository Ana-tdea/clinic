package app.domain.services;

import app.domain.Exceptions.BusinessException;
import app.domain.models.User;
import app.domain.ports.UserPort;

public class CreateUser {

    private UserPort userPort;

    public void ceateUser (User user) throws BusinessException {
        ///Vamos a validar que no haya otro usuario con la mismma cedula
        ///Para eso debemos consultar a la base de datos si existe un usuario con esa cedula
        ///Si existe, lanzamos una excepcion
        if (userPort.existsByDocument(user.getDocument())) {
            throw new BusinessException("Ya existe un usuario con esa cedula");
        }
        ///Validamos la existencia del usuario por UserName
        ///En caso de existir lanzamos una excepcion
        if (userPort.existsByUserName(user.getUsername())) {
            throw new BusinessException("Ya existe un usuario con ese nombre de usuario");
        }
        ///si no existe, podemos crear el usuario
        userPort.save(user);

    }

}
