package entidades;

/**
 *
 * @author Jonathan Flores
 */
public class Correos
{
    private int noUsuario;
    private String tipo;
    private String correo;

    public Correos(int noUsuario, String tipo, String correo)
    {
        this.noUsuario = noUsuario;
        this.tipo = tipo;
        this.correo = correo;
    }

    public int getNoUsuario()
    {
        return noUsuario;
    }

    public void setNoUsuario(int noUsuario)
    {
        this.noUsuario = noUsuario;
    }

    public String getTipo()
    {
        return tipo;
    }

    public void setTipo(String tipo)
    {
        this.tipo = tipo;
    }

    public String getCorreo()
    {
        return correo;
    }

    public void setCorreo(String correo)
    {
        this.correo = correo;
    }
    
    @Override
    public String toString()
    {
        return "Correo("
                + "noUsuario=" + noUsuario
                + ", tipo=" + tipo
                + ", correo=" + correo
                + ")";
    }
}
