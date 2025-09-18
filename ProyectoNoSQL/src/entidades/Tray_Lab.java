package entidades;
import java.util.Date;
/**
 *
 * @author Jonathan Flores
 */
public class Tray_Lab
{
    private int noUsuario;
    private Date fechaAlta;
    private String usuarioContrato;

    public Tray_Lab(int noUsuario, Date fechaAlta, String usuarioContrato)
    {
        this.noUsuario = noUsuario;
        this.fechaAlta = fechaAlta;
        this.usuarioContrato = usuarioContrato;
    }

    public int getNoUsuario()
    {
        return noUsuario;
    }

    public void setNoUsuario(int noUsuario)
    {
        this.noUsuario = noUsuario;
    }

    public Date getFechaAlta()
    {
        return fechaAlta;
    }

    public void setFechaAlta(Date fechaAlta)
    {
        this.fechaAlta = fechaAlta;
    }

    public String getUsuarioContrato()
    {
        return usuarioContrato;
    }

    public void setUsuarioContrato(String usuarioContrato)
    {
        this.usuarioContrato = usuarioContrato;
    }
    
    @Override
    public String toString()
    {
        return "Tray_Lab(" +
                "noUsuario=" + noUsuario +
                ", fechaAlta=" + fechaAlta +
                ", usrContrato=" + usuarioContrato +
                ")";
    }
}