package metodos;

import java.util.ResourceBundle;

public class Traduccion {
    private static ResourceBundle bundle;
    private static String idioma = "Español";
    
    public static void cambiarIdioma(String nuevoIdioma) {
        switch (nuevoIdioma) {
            case "Ingles":
                bundle = ResourceBundle.getBundle("Resources/Textos_en");
                break;
            case "Portugués":
                bundle = ResourceBundle.getBundle("Resources/Textos_pt");
                break;
            default:
                bundle = ResourceBundle.getBundle("Resources/Textos");
                break;
        }
        idioma = nuevoIdioma;
    }

    public static ResourceBundle getBundle() {
        if (bundle == null) {
            cambiarIdioma(idioma);
        }
        return bundle;
    }

    public static String getIdioma() {
        return idioma;
    }
}
