
package com.digis01.DAraizaProgramacionNCapasMaven.ML;


import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;


public class Usuario {
    
    private int IdUsuario;
    
    
    private String Nombre;
    

    private String ApellidoPaterno;
    
    
    private String ApellidoMaterno;
    
   
    private String NumeroTelefonico;
    

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date FechaNacimiento;
    
//    @Pattern(regexp = "^([A-Z][AEIOUX][A-Z]{2}\\d{2}(?:0[1-9]|1[0-2])(?:0[1-9]|[12]\\d|3[01])[HM](?:AS|B[CS]|C[CLMSH]|D[FG]|G[TR]|HG|JC|M[CNS]|N[ETL]|OC|PL|Q[TR]|S[PLR]|T[CSL]|VZ|YN|ZS)[B-DF-HJ-NP-TV-Z]{3}[A-Z\\d])(\\d)$", message = "Formato invalido")
    private String CURP;
    
//    @Pattern(regexp = "^[a-zA-Z ]+[0-9]+$", message = "Introduzca minimo una letra y minimo un numero")
    private String Username;
    
    private String Sexo;
    
//    @Pattern(regexp = "^[0-9]{10}$", message = "Solo numeros")
    private String Celular;
    
//    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z]+.com$", message = "formato invalido")
    private String Email;
    
    private String Password;
    
    public com.digis01.DAraizaProgramacionNCapasMaven.ML.Rol Rol; 
    
    public String Imagen;
    
    public int Status;

    
    public List<com.digis01.DAraizaProgramacionNCapasMaven.ML.Direccion> Direcciones;

    public Usuario(int i, String dilan, Object object, Object object0, Object object1, Object object2, Object object3, Object object4, Object object5, Object object6) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    //setters
    public int getIdUsuario(){
        return IdUsuario;
    }
    
    public void setIdUsuario(int IdUsuario){
        this.IdUsuario = IdUsuario;
    }

    public Usuario(int IdUsuario, String Nombre, String ApellidoPaterno, String ApellidoMaterno, String NumeroTelefonico, Date FechaNacimiento, String CURP, String Username, String Sexo, String Celular, String Email, String Imagen,int Status,String Password, Rol Rol, List<Direccion> Direcciones) {
        this.IdUsuario = IdUsuario;
        this.Nombre = Nombre;
        this.ApellidoPaterno = ApellidoPaterno;
        this.ApellidoMaterno = ApellidoMaterno;
        this.NumeroTelefonico = NumeroTelefonico;
        this.FechaNacimiento = FechaNacimiento;
        this.CURP = CURP;
        this.Username = Username;
        this.Sexo = Sexo;
        this.Celular = Celular;
        this.Email = Email;
        this.Status = Status;
        this.Password = Password;
        this.Rol = Rol;
        this.Direcciones = Direcciones;
        this.Imagen = Imagen;
    }
    
    
    public Usuario(){
    }

   

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
    public String getNombre(){
        return Nombre;
    }
    
    public void setApellidoPaterno(String ApellidoPaterno){
        this.ApellidoPaterno = ApellidoPaterno;
        
    }
    
    public String getApellidoPaterno(){
        return ApellidoPaterno;
    }
    
    public void setApellidoMaterno (String ApellidoMaterno){
        this.ApellidoMaterno = ApellidoMaterno;
    }
    
    public String getApellidoMaterno (){
        return ApellidoMaterno;
    }
     

    public void setFechaNacimiento(Date FechaNacimiento){
        this.FechaNacimiento = FechaNacimiento;
    }
    
    public Date getFechaNacimiento(){
        return FechaNacimiento;
    }
    
    public void setCURP(String CURP){
        this.CURP = CURP;
    }
    
    public String getCURP(){
        return CURP;
    }
    
    public void setNumeroTelefonico(String NumeroTelefonico){
        this.NumeroTelefonico = NumeroTelefonico;
    }
    
    public String getNumeroTelefonico (){
        return NumeroTelefonico;
    } 
    
    public void setUsername(String Username){
        this.Username = Username;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String Imagen) {
        this.Imagen = Imagen;
    }
    
    public String getUsername(){
        return Username;
    }
    
    
    public void setSexo(String Sexo){
        this.Sexo = Sexo;
    }
    
    public String getSexo(){
        return Sexo;
    }
    
    public void setCelular(String Celular){
        this.Celular =  Celular;
    }
    
    public String getCelular(){
        return Celular;
    }
    
    public void setEmail(String Email){
        this.Email = Email;
    }
    
    public String getEmail (){
        return Email;
    }
    
    public void setPassword(String Password){
        this.Password = Password;
    }
    
    public String getPassword(){
        return Password;
    }

    public Rol getRol() {
        return Rol;
    }

    public void setRol(Rol Rol) {
        this.Rol = Rol;
    }

    public List<Direccion> getDirecciones() {
        return Direcciones;
    }

    public void setDirecciones(List<Direccion> Direcciones) {
        this.Direcciones = Direcciones;
    }

    public Object Rol() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
        public int getStatus() {
        return Status;
    }

    public void setStatus(int Status) {
        this.Status = Status;
    }
 
}
