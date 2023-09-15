package com.mycompany.desafio1;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.HttpURLConnection;
import java.net.URL;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author johnc
 */
public class ALDAL {

    public static void geraTabela(Object obj) {
        Field[] f = obj.getClass().getDeclaredFields();
        String sql = "Create Table Tab" + obj.getClass().getSimpleName() + " (";

        for (int i = 0; i < f.length; ++i) {
            sql += f[i].getName() + " " + (f[i].getType().getSimpleName().equals("String") ? "varchar(60)" : f[i].getType());
            if (i != (f.length - 1)) {
                sql = sql + ", ";
            }
        }
        sql += ")";
        System.out.println(sql);
        AFDAL.conecta("C:\\Users\\maria\\Documents\\NetBeansProjects\\Desafio1\\src\\Enderecos.accdb");
        AFDAL.executeSQL(sql);
        AFDAL.desconecta();
    }

    public static void set(Object obj) {
        Field[] f = obj.getClass().getDeclaredFields();
        String sql = "Insert Into Tab" + obj.getClass().getSimpleName() + " (";
        Method mtd;

        for (int i = 0; i < f.length; ++i) {
            if (f[i].getName().toCharArray()[0] == '_') {
                continue;
            }
            sql += f[i].getName();
            if (i != (f.length - 1)) {
                sql = sql + ", ";
            }
            
        }
        sql += ") values (";
        for (int i = 0; i < f.length; ++i) {
            if (f[i].getName().toCharArray()[0] == '_') {
                continue;
            }
            try {
                String aux = "get" + f[i].getName().substring(0, 1).toUpperCase() + f[i].getName().substring(1);
                mtd = obj.getClass().getMethod(aux);

                if (f[i].getType().getSimpleName().equals("String")) {
                    sql += "'" + mtd.invoke(obj) + "'";
                } else {
                    sql += mtd.invoke(obj);
                }
            } catch (Exception e) {
            }
            if (i != (f.length - 1)) {
                sql = sql + ", ";
            }
        }
        sql += ")";
        System.out.println(sql);
        AFDAL.conecta("C:\\Users\\maria\\Documents\\NetBeansProjects\\Desafio1\\src\\Enderecos.accdb");
        AFDAL.executeSQL(sql);
        AFDAL.desconecta();
    }

    public static void delete(Object obj) {
        Field[] f = obj.getClass().getDeclaredFields();
        String sql = "Delete from Tab" + obj.getClass().getSimpleName() + " where ";
        Method mtd;
        String aux1, aux2;
        boolean flag = false;

        for (int i = 0; i < f.length; ++i) {
            try {
                aux1 = "get" + f[i].getName().substring(0, 1).toUpperCase() + f[i].getName().substring(1);
                mtd = obj.getClass().getMethod(aux1);
                aux2 = mtd.invoke(obj).toString();
                if (!aux2.equals("")) {
                    if (flag) {
                        sql += " and ";
                    } else {
                        flag = true;
                    }
                    sql += f[i].getName() + " = ";
                    if (f[i].getType().getSimpleName().equals("String")) {
                        sql += "'" + aux2 + "'";
                    } else {
                        sql += aux2;
                    }
                }
            } catch (Exception e) {
            }
        }

        System.out.println(sql);
        AFDAL.conecta("C:\\Users\\maria\\Documents\\NetBeansProjects\\Desafio1\\src\\Enderecos.accdb");
        AFDAL.executeSQL(sql);
        AFDAL.desconecta();
    }

    public static void update(Object dados, Object chaves) {
        Field[] f = dados.getClass().getDeclaredFields();
        String sql = "Update Tab" + dados.getClass().getSimpleName() + " set ";
        Method mtd;
        String aux1, aux2;
        boolean flag = false;

        for (int i = 0; i < f.length; ++i) {
            try {
                aux1 = "get" + f[i].getName().substring(0, 1).toUpperCase() + f[i].getName().substring(1);
                mtd = dados.getClass().getMethod(aux1);
                aux2 = mtd.invoke(dados).toString();
                if (!aux2.equals("")) {
                    if (flag) {
                        sql += ", ";
                    } else {
                        flag = true;
                    }
                    sql += f[i].getName() + " = ";
                    if (f[i].getType().getSimpleName().equals("String")) {
                        sql += "'" + aux2 + "'";
                    } else {
                        sql += aux2;
                    }
                }
            } catch (Exception e) {
            }
        }

        sql += " where ";
        f = chaves.getClass().getDeclaredFields();
        flag = false;

        for (int i = 0; i < f.length; ++i) {
            try {
                aux1 = "get" + f[i].getName().substring(0, 1).toUpperCase() + f[i].getName().substring(1);
                mtd = chaves.getClass().getMethod(aux1);
                aux2 = mtd.invoke(chaves).toString();
                if (!aux2.equals("")) {
                    if (flag) {
                        sql += " and ";
                    } else {
                        flag = true;
                    }
                    sql += f[i].getName() + " = ";
                    if (f[i].getType().getSimpleName().equals("String")) {
                        sql += "'" + aux2 + "'";
                    } else {
                        sql += aux2;
                    }
                }
            } catch (Exception e) {
            }
        }

        System.out.println(sql);
        AFDAL.conecta("C:\\Users\\maria\\Documents\\NetBeansProjects\\Desafio1\\src\\Enderecos.accdb");
        AFDAL.executeSQL(sql);
        AFDAL.desconecta();
    }

    public static void get(Object obj) {
        Field[] f = obj.getClass().getDeclaredFields();
        String sql = "Select * from Tab" + obj.getClass().getSimpleName() + " where ";
        Method mtd;
        String aux1, aux2;
        boolean flag = false;

        for (int i = 0; i < f.length; ++i) {
            try {
                aux1 = "get" + f[i].getName().substring(0, 1).toUpperCase() + f[i].getName().substring(1);
                mtd = obj.getClass().getMethod(aux1);
                aux2 = mtd.invoke(obj).toString();
                if (!aux2.equals("")) {
                    if (flag) {
                        sql += " and ";
                    } else {
                        flag = true;
                    }
                    sql += f[i].getName() + " = ";
                    if (f[i].getType().getSimpleName().equals("String")) {
                        sql += "'" + aux2 + "'";
                    } else {
                        sql += aux2;
                    }
                    System.out.println(aux2);
                }
            } catch (Exception e) {
            }
        }

        System.out.println(sql);

        AFDAL.conecta("C:\\Users\\maria\\Documents\\NetBeansProjects\\Desafio1\\src\\Enderecos.accdb");
        AFDAL.executeSelect(sql, obj);
        AFDAL.desconecta();
    }
    
    private static String extrairValor(String json, String chave) {
    int startIndex = json.indexOf("\"" + chave + "\"") + chave.length() + 3; // +3 para pular ": "
    int endIndex = json.indexOf("\"", startIndex);
    return json.substring(startIndex, endIndex);
}
    
    public static void setCep(String cep) throws Exception {
      String url = "http://viacep.com.br/ws/" + cep + "/json/";
      HttpURLConnection connection = (HttpURLConnection)(new URL(url)).openConnection();
      connection.setRequestMethod("GET");
      int responseCode = connection.getResponseCode();
      if (responseCode != 200) {
         throw new Exception("Erro ao buscar informa\u00e7\u00f5es do CEP. C\u00f3digo de resposta: " + responseCode);
      } else {
         BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
         StringBuilder response = new StringBuilder();

         String line;
         while((line = reader.readLine()) != null) {
            response.append(line);
         }

         reader.close();
         DadosJson cepDTO = new DadosJson();
         System.out.println(response.toString());
         AFDAL.deserializaJSON(response.toString().toCharArray(), cepDTO);
         System.out.println(cepDTO.getlogradouro());
         /*this.bairro = cepDTO.getBairro();
         this.cidade = cepDTO.getUf();
         this.estado = cepDTO.getLocalidade();
         this.rua = cepDTO.getLogradouro();
         this.setLatLng();*/
      }
   }

}
