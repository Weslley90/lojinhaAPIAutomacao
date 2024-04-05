package dataFactory;

import pojo.UsuarioPojo;

public class UsuarioFactory {
    public static UsuarioPojo criarUsuario (){
        UsuarioPojo usuario = new UsuarioPojo();
        usuario.setUsuarioLogin("admin");
        usuario.setUsuarioSenha("admin");
        return usuario;
    }
}
