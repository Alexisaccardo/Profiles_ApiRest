package com.example.demo;

import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class Controlador {

    @PostMapping("/register_users")
    public Users register_users(@RequestBody Users users) throws SQLException, ClassNotFoundException {

        String code = users.getCode();
        String name = users.getName();
        String charge = users.getCharge();
        String nit = users.getNit();
        String cellphone = users.getCellphone();

        if (code == null || code.equals("") || code.length() < 0 || name == null || name.equals("") || name.length() < 0 ||
                charge == null || nit == null || nit.equals("") || nit.length() < 0 ||
                cellphone == null || cellphone.equals("") || cellphone.length() < 0) {

            return new Users(null, null, null, null, null);

        } else {
            if (nit.equals("5625")) {
                BD bd = new BD();
                users = bd.Register(code, name, "Administrador", nit, cellphone);
            } else {
                BD bd = new BD();
                if (charge.equals("")) {
                    return new Users(null, null, null, null, null);
                } else {
                    users = bd.Register(code, name, charge, nit, cellphone);
                }
            }
            return users;

        }
    }

    @PostMapping("/edit_users")
    public Users edit_users(@RequestBody Users users) throws SQLException, ClassNotFoundException {

        String code = users.getCode();
        String name = users.getName();
        String charge = users.getCharge();
        String nit = users.getNit();
        String cellphone = users.getCellphone();

        if (code == null || code.equals("") || code.length() < 0 || name == null || name.equals("") || name.length() < 0 ||
                charge == null || charge.equals("") || charge.length() < 0 || nit == null || nit.equals("") || nit.length() < 0 ||
                cellphone == null || cellphone.equals("") || cellphone.length() < 0) {

            return new Users(null, null, null, null, null);
        } else {
            if (nit.equals("5625")) {

                return new Users(null, null, null, "No se puede editar un nit de administrador", null);
            } else {
                BD bd = new BD();
                users = bd.Edit(code, name, charge, nit, cellphone);

            }
        }
        return users;
    }

    @GetMapping("/search_users")
    public List<Users> search_users() throws SQLException, ClassNotFoundException {
        BD bd = new BD();
        List<Users> list = bd.Select_consult();

        return list;

    }

    @DeleteMapping("/delete_users")
    public Users delete_users(@RequestBody Users users) throws SQLException, ClassNotFoundException {
        String code = users.getCode();
        String nit = users.getNit();

        if (users.getCode() == null || users.getCode().equals("") || users.getCode().length() < 0
                || users.getNit() == null || users.getNit().equals("") || users.getNit().length() < 0) {

            return new Users(null, null, null, null, null);

        } else {
            if (!nit.equals("5625")) {

                BD bd = new BD();
                int f = bd.Delete(code);
                if (f == 0) {
                    return new Users("No se encuentra un usuario con el codigo especificado para eliminar", null, null, null, null);


                }
            }else{
                return new Users(null, null, null, "El nit ingresado no se puede eliminar, pertenece a administrador", null);
            }
        }

        return users;
    }
}

