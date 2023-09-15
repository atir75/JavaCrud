package com.mycompany.desafio1;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author johnc
 */
import java.sql.*;
import java.io.*;
import java.lang.reflect.*;

public class AFDAL {

    private static Connection con;

    public static void conecta(String _bd) {
        Erro.setErro(false);
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            con = DriverManager.getConnection("jdbc:ucanaccess://" + _bd);
            System.out.println("conectou");
        } catch (Exception e) {
            Erro.setErro(e.getMessage());
        }
    }

    /*public static void desconecta()
    {
        Erro.setErro(false);
        try 
        {
            con.close();
        }
        catch (Exception e)
        {
            Erro.setErro(e.getMessage());
        }
    }*/
    public static void desconecta() {
        try {
            con.close();
        } catch (Exception e) {
            Erro.setErro(e.getMessage());
        }
    }

    public static void executeSQL(String _sql) {
        Erro.setErro(false);
        try {
            Statement st = con.createStatement();
            st.executeUpdate(_sql);
            st.close();
        } catch (Exception e) {
            Erro.setErro(e.getMessage());
        }
    }

    public static void geraClasse(String _tabela, String _package) {
        File arquivo = new File(_tabela + ".java");
        ResultSet rs;
        ResultSetMetaData rsmd;

        conecta("Enderecos.mdb");
        Erro.setErro(false);
        try {
            Statement st = con.createStatement();
            rs = st.executeQuery("Select * from " + _tabela);
            rsmd = rs.getMetaData();
            String aux;

            FileWriter fw = new FileWriter(arquivo);
            fw.write("package " + _package + ";\n\n");
            fw.write("public class " + _tabela + " {\n\n");
            fw.write("public " + _tabela + "() {}\n\n");
            for (int i = 1; i <= rsmd.getColumnCount(); ++i) {
                aux = rsmd.getColumnName(i);
                fw.write("private String " + aux + ";\n");
                fw.write("public void set" + aux.substring(0, 1).toUpperCase() + aux.substring(1) + "(String _" + aux + ") { " + aux + " = _" + aux + "; }\n");
                fw.write("public String get" + aux.substring(0, 1).toUpperCase() + aux.substring(1) + "() { return " + aux + "; }\n\n");
            }
            fw.write("}\n");
            fw.flush();
            fw.close();
            st.close();
        } catch (Exception e) {
            Erro.setErro(e.getMessage());
        }
        desconecta();
    }

    public static void executeSelect(String _sql, Object obj) {
        ResultSet rs;
        Erro.setErro(false);
        try {
            PreparedStatement st = con.prepareStatement(_sql);
            rs = st.executeQuery();
            if (rs.next()) {
                Field[] f = obj.getClass().getDeclaredFields();
                Method mtd;
                String aux;
                for (int i = 0; i < f.length; ++i) {
                    aux = "set" + f[i].getName().substring(0, 1).toUpperCase() + f[i].getName().substring(1);
                    try {
                        mtd = obj.getClass().getMethod(aux, new Class[]{f[i].getType()});
                        mtd.invoke(obj, new Object[]{rs.getString(f[i].getName())});
                    } catch (Exception e) {
                    }
                }
            } else {
                Erro.setErro(obj.getClass().getSimpleName() + " não localizado.");
                return;
            }
            st.close();
        } catch (Exception e) {
            Erro.setErro(e.getMessage());
        }
    }
    
    public static void deserializaJSON(char[] json, Object obj) {
      char[] buffer = json;
      String aux = "";
      String propriedade = "";
      String dado = "";
      String metodo = "";
      boolean flag = true;

      try {
         for(int i = 0; i < buffer.length; ++i) {
            if (buffer[i] == '"') {
               ++i;

               while(buffer[i] != '"') {
                  aux = aux + buffer[i];
                  ++i;
               }

               if (flag) {
                  propriedade = aux;
               } else {
                  metodo = "set" +  propriedade;
                  Method mtd = obj.getClass().getMethod(metodo, aux.getClass());
                  mtd.invoke(obj, aux);
               }

               flag = !flag;
               aux = "";
            }
         }
      } catch (Exception var10) {
         System.out.println(var10.getMessage());
      }

   }

}
