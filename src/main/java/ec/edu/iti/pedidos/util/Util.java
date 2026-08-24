package ec.edu.iti.pedidos.util;

public class Util {

    private Util() {
        // Evita crear objetos de esta clase
    }

    public static boolean validarCedula(String cedula) {

        // Verificar que no sea null
        if (cedula == null) {
            return false;
        }

        // Eliminar espacios
        cedula = cedula.trim();

        // La cédula ecuatoriana tiene 10 dígitos
        if (cedula.length() != 10) {
            return false;
        }

        // Verificar que todos sean números
        if (!cedula.matches("\\d{10}")) {
            return false;
        }

        // Verificar provincia
        int provincia = Integer.parseInt(cedula.substring(0, 2));

        if (provincia < 1 || provincia > 24) {
            return false;
        }

        // El tercer dígito debe ser menor que 6
        int tercerDigito = Character.getNumericValue(cedula.charAt(2));

        if (tercerDigito >= 6) {
            return false;
        }

        // Cálculo del dígito verificador
        int suma = 0;

        for (int i = 0; i < 9; i++) {

            int digito = Character.getNumericValue(cedula.charAt(i));

            if (i % 2 == 0) {

                int resultado = digito * 2;

                if (resultado >= 10) {
                    resultado -= 9;
                }

                suma += resultado;

            } else {

                suma += digito;
            }
        }

        int decenaSuperior = ((suma / 10) + 1) * 10;

        int digitoVerificador = decenaSuperior - suma;

        if (digitoVerificador == 10) {
            digitoVerificador = 0;
        }

        int ultimoDigito =
                Character.getNumericValue(cedula.charAt(9));

        return digitoVerificador == ultimoDigito;
    }
}