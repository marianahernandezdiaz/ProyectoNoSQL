package entidades;

/**
 *
 * @author Jonathan Flores
 */
public class Cat_Estados
{
    private String idEstado;
    private String nomEstado;
    private String pais;
    private String estatus;

    public String getIdEstado()
    {
        return idEstado;
    }

    public void setIdEstado(String idEstado)
    {
        this.idEstado = idEstado;
    }

    public String getNomEstado()
    {
        return nomEstado;
    }

    public void setNomEstado(String nomEstado)
    {
        this.nomEstado = nomEstado;
    }

    public String getPais()
    {
        return pais;
    }

    public void setPais(String pais)
    {
        this.pais = pais;
    }

    public String getEstatus()
    {
        return estatus;
    }

    public void setEstatus(String estatus)
    {
        this.estatus = estatus;
    }

    public Cat_Estados(String idEstado, String nomEstado, String pais, String estatus)
    {
        this.idEstado = idEstado;
        this.nomEstado = nomEstado;
        this.pais = pais;
        this.estatus = estatus;
    }
    
    @Override
    public String toString()
    {
        return "cat_estado(" +
                "idEstado=" + idEstado +
                ", nomEstado=" + nomEstado +
                ", pais=" + pais +
                ", estatus=" + estatus +
                ")";
    }
}
