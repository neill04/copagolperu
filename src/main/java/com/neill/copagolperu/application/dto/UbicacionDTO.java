package com.neill.copagolperu.application.dto;

public class UbicacionDTO {

    public static class ProvinciaDTO {
        private Long id;
        private String nombre;

        public ProvinciaDTO(Long id, String nombre) {
            this.id = id;
            this.nombre = nombre;
        }

        public Long getId() { return id;}
        public void setId(Long id) { this.id = id; }
        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }
    }

    public static class DistritoDTO {
        private Long id;
        private String nombre;

        public DistritoDTO(Long id, String nombre) {
            this.id = id;
            this.nombre = nombre;
        }

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getNombre() { return nombre; }
        public void setNombre(String nombre) { this.nombre = nombre; }
    }
}