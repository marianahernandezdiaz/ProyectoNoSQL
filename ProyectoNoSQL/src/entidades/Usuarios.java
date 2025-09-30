package entidades;
/**
 *
 * @author Jonathan Esquivel Flores
 */
public class Usuarios 
{
    private int noUsuario;
    private String nombre;
    private String apPat;
    private String apMat;
    private String tcorreo;
    private String curp;
    private String rfc;
    private int telefono;
    private String idDepto;
    private String sexo;
    private String estatus;

    public Usuarios(int idEmpleado, String nombre, String priAp, String secAp, String tcorreo, String curp, String rfc, int telefono, String idDepto, String sexo, String estatus) 
    {
        this.noUsuario = idEmpleado;
        this.nombre = nombre;
        this.apPat = priAp;
        this.apMat = secAp;
        this.tcorreo = tcorreo;
        this.curp = curp;
        this.rfc = rfc;
        this.telefono = telefono;
        this.idDepto = idDepto;
        this.sexo = sexo;
        this.estatus = estatus;
    }
    
    public Usuarios()
    {
        
    }

    public int getNoUsuario() {
        return noUsuario;
    }
    public void setNoUsuario(int noUsuario) {
        this.noUsuario = noUsuario;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApPat() {
        return apPat;
    }
    public void setApPat(String apPat) {
        this.apPat = apPat;
    }

    public String getApMat() {
        return apMat;
    }
    public void setApMat(String apMat) {
        this.apMat = apMat;
    }

    public String getSexo() {
        return sexo;
    }
    public void setSexo(String genero) {
        this.sexo = genero;
    }

    public String getEstatus() {
        return estatus;
    }
    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getTcorreo()
    {
        return tcorreo;
    }

    public void setTcorreo(String tcorreo)
    {
        this.tcorreo = tcorreo;
    }

    public String getCurp()
    {
        return curp;
    }

    public void setCurp(String curp)
    {
        this.curp = curp;
    }

    public String getRfc()
    {
        return rfc;
    }

    public void setRfc(String rfc)
    {
        this.rfc = rfc;
    }

    public int getTelefono()
    {
        return telefono;
    }

    public void setTelefono(int telefono)
    {
        this.telefono = telefono;
    }

    public String getIdDepto()
    {
        return idDepto;
    }

    public void setIdDepto(String idDepto)
    {
        this.idDepto = idDepto;
    }
    
    @Override
    public String toString() {
        return "Empleado{" + 
       "noUsuario=" + noUsuario + 
       ", nombre=" + nombre + 
       ", apPat=" + apPat + 
       ", apMat=" + apMat + 
       ", tcorreo=" + tcorreo + 
       ", curp=" + curp + 
       ", rfc=" + rfc + 
       ", telefono=" + telefono + 
       ", idDepto=" + idDepto + 
       ", sexo=" + sexo + 
       ", estatus=" + estatus + 
       '}';
    }
}
