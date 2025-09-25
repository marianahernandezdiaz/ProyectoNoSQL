package entidades;

/**
 *
 * @author Jonathan Flores
 */
public class Domicilio
{
    private int noUsuario;
    private String calle;
    private String noExt;
    private String noInt;
    private int cp;
    private String colonia;
    private String idMunicipio;

    public Domicilio(int noUsuario, String calle, String noExt, String noInt, int cp, String colonia, String idMunicipio)
    {
        this.noUsuario = noUsuario;
        this.calle = calle;
        this.noExt = noExt;
        this.noInt = noInt;
        this.cp = cp;
        this.colonia = colonia;
        this.idMunicipio = idMunicipio;
    }

    public int getNoUsuario()
    {
        return noUsuario;
    }

    public void setNoUsuario(int noUsuario)
    {
        this.noUsuario = noUsuario;
    }

    public String getCalle()
    {
        return calle;
    }

    public void setCalle(String calle)
    {
        this.calle = calle;
    }

    public String getNoExt()
    {
        return noExt;
    }

    public void setNoExt(String noExt)
    {
        this.noExt = noExt;
    }

    public String getNoInt()
    {
        return noInt;
    }

    public void setNoInt(String noInt)
    {
        this.noInt = noInt;
    }

    public int getCp()
    {
        return cp;
    }

    public void setCp(int cp)
    {
        this.cp = cp;
    }

    public String getColonia()
    {
        return colonia;
    }

    public void setColonia(String colonia)
    {
        this.colonia = colonia;
    }

    public String getIdMunicipio()
    {
        return idMunicipio;
    }

    public void setIdMunicipio(String idMunicipio)
    {
        this.idMunicipio = idMunicipio;
    }
    
    @Override
    public String toString()
    {
        return "Domicilio(" +
                "noUsuario=" + noUsuario +
                ", calle=" + calle +
                ", noExt=" + noExt +
                ", noInt=" + noInt +
                ", cp=" + cp +
                ", colonia=" + colonia +
                ", idMunicipio=" + idMunicipio +
                ")";
    }
}
