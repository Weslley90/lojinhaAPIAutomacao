package dataFactory;

import pojo.UsuarioPojo;

public class UsuarioFactory {
    public static UsuarioPojo criarUsuario (){
        UsuarioPojo usuario = new UsuarioPojo();
        usuario.setUsuarioLogin("nome_do_usuario");
        usuario.setUsuarioSenha("senha_do_usuario");
        return usuario;
    }
}
