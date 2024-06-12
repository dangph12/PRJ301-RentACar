/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.util.UUID;

/**
 *
 * @author admin
 */
public class Util {
    public String generateUserUID() {
        UUID uuid = UUID.randomUUID();
        String userUID = uuid.toString();
        return userUID;
    }

    public Util() {
    }
}
