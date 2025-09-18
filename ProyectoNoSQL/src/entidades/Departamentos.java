package entidades;

/**
 *
 * @author Jonathan Flores
 */
public class Departamentos
{
    private String idDeptos;
    private String nomDepto;
    private String estatus;

    public Departamentos(String idDeptos, String nomDepto, String estatus)
    {
        this.idDeptos = idDeptos;
        this.nomDepto = nomDepto;
        this.estatus = estatus;
    }

    public String getIdDeptos()
    {
        return idDeptos;
    }

    public void setIdDeptos(String idDeptos)
    {
        this.idDeptos = idDeptos;
    }

    public String getNomDepto()
    {
        return nomDepto;
    }

    public void setNomDepto(String nomDepto)
    {
        this.nomDepto = nomDepto;
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
        return "Departamentos(" +
                "idDepto=" + idDeptos +
                ", nomDepto=" + nomDepto +
                ", estatus=" + estatus +
                ")";
    }
}
