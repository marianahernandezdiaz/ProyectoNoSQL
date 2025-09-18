package entidades;

/**
 *
 * @author Jonathan Flores
 */
public class Cat_Municipios
{
    private String idMunicipio;
    private String nomMunicipio;
    private String idEstado;
    private String estatus;

    public Cat_Municipios(String idMunicipio, String nomMunicipio, String idEstado, String estatus)
    {
        this.idMunicipio = idMunicipio;
        this.nomMunicipio = nomMunicipio;
        this.idEstado = idEstado;
        this.estatus = estatus;
    }

    public String getIdMunicipio()
    {
        return idMunicipio;
    }

    public void setIdMunicipio(String idMunicipio)
    {
        this.idMunicipio = idMunicipio;
    }

    public String getNomMunicipio()
    {
        return nomMunicipio;
    }

    public void setNomMunicipio(String nomMunicipio)
    {
        this.nomMunicipio = nomMunicipio;
    }

    public String getIdEstado()
    {
        return idEstado;
    }

    public void setIdEstado(String idEstado)
    {
        this.idEstado = idEstado;
    }

    public String getEstatus()
    {
        return estatus;
    }

    public void setEstatus(String estatus)
    {
        this.estatus = estatus;
    }
    
    @Override
    public String toString()
    {
        return "Cat_Municipios(" +
                "idMunicipio=" + idMunicipio +
                ", nomMunicipio=" + nomMunicipio +
                ", idEstado=" + idEstado +
                ", estatus=" + estatus +
                ")";
    }
}