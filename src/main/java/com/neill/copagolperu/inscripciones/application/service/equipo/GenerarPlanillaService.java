package com.neill.copagolperu.inscripciones.application.service.equipo;

import com.neill.copagolperu.inscripciones.domain.model.Equipo;
import com.neill.copagolperu.inscripciones.domain.model.Jugador;
import com.neill.copagolperu.inscripciones.domain.repository.EquipoRepository;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

@Service
public class GenerarPlanillaService {

    @Autowired
    private EquipoRepository equipoRepository;

    public byte[] generarPlanillaExcel(UUID equipoId) throws IOException {
        Equipo equipo = equipoRepository.findById(equipoId)
                .orElseThrow(() -> new RuntimeException("Equipo no encontrado"));

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Planilla");

        // Ocultar gridlines
        sheet.setDisplayGridlines(false);

        // Estilos
        CellStyle titleStyle = createTitleStyle(workbook);
        CellStyle headerStyle = createHeaderStyle(workbook);
        CellStyle defaultBorderStyle = createDefaultBorderStyle(workbook);

        for (int i = 0; i < 26; i++) {
            sheet.setColumnWidth(i, 1075);
        }

        for (int i = 0; i < 38; i++) {
            Row row = sheet.createRow(i);
            row.setHeightInPoints(19);

            for (int j = 0; j < 26; j++) {
                Cell cell = row.createCell(j);
                cell.setCellValue("");
                cell.setCellStyle(defaultBorderStyle);
            }
        }

        // Filas 1 y 2 (índices 0 y 1) fusionadas en 3 secciones

        // Sección A-E (columnas 0-4, filas 1-2)
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 4));

        // FILA 3 (A - E) = N° FECHA
        Row row2 = sheet.getRow(2);
        Cell fechaCell = row2.createCell(0);
        fechaCell.setCellValue("N° FECHA");
        fechaCell.setCellStyle(headerStyle);
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 4));

        // Sección F-U (columnas 5-20, filas 1-2) - TÍTULO
        Row row1 = sheet.getRow(0);
        Cell titleCell = row1.createCell(5);
        titleCell.setCellValue("11° COPA GOL PERÚ");
        titleCell.setCellStyle(titleStyle);
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 5, 20));

        Row row3 = sheet.getRow(2);
        Cell ligaCell = row3.createCell(5);
        ligaCell.setCellValue("LIGA JUVENIL GOL PERU");
        ligaCell.setCellStyle(createLigaStyle(workbook));
        sheet.addMergedRegion(new CellRangeAddress(2, 3, 5, 20));

        // Sección V-Z (columnas 21-25, filas 1-2)
        sheet.addMergedRegion(new CellRangeAddress(0, 1, 21, 25));
        sheet.addMergedRegion(new CellRangeAddress(2, 2, 21, 25));

        // Filas 4 y 5 (índices 3 y 4)

        // FILA 4 Y 5 (A - C)
        Row row4 = sheet.getRow(3);
        Cell grupoCell = row4.createCell(0);
        grupoCell.setCellValue("GRUPO");
        grupoCell.setCellStyle(headerStyle);
        sheet.addMergedRegion(new CellRangeAddress(3, 4, 0, 2));

        // ESPACIO PARA EL GRUPO
        sheet.addMergedRegion(new CellRangeAddress(3, 4, 3, 4));

        // Solo fila 5 (índice 4) desde F-U (columnas 5-20)
        sheet.addMergedRegion(new CellRangeAddress(4, 4, 5, 20));

        // FILA 3 (V - Z)
        Cell partidoCell = row2.createCell(21);
        partidoCell.setCellValue("N° PARTIDO");
        partidoCell.setCellStyle(headerStyle);
        sheet.addMergedRegion(new CellRangeAddress(3, 4, 21, 25));

        int categoriaNumero = extraerNumeroCategoria(equipo.getCategoria().name());

        // FILA 6,7 Y 8 (A - B) = CATEGORIA
        Row row6 = sheet.getRow(5);
        Cell categoriaCell = row6.createCell(0);
        categoriaCell.setCellValue(categoriaNumero);
        categoriaCell.setCellStyle(createCategoriaStyle(workbook));
        sheet.addMergedRegion(new CellRangeAddress(5, 7, 0, 1));

        int anoActual = LocalDate.now().getYear();
        int anoMenor = anoActual - categoriaNumero;
        int anoMayor = anoMenor + 1;

        // FILA 6 (C - E)
        Cell anoMenorCell = row6.createCell(2);
        anoMenorCell.setCellValue(anoMenor);
        anoMenorCell.setCellStyle(createHeaderStyle(workbook));
        sheet.addMergedRegion(new CellRangeAddress(5, 5, 2, 4));

        // (V - Z)
        Row row6_v = sheet.getRow(5);
        Cell horaCell = row6_v.createCell(21);
        horaCell.setCellValue("HORA DE JUEGO");
        horaCell.setCellStyle(createHoraStyle(workbook));
        sheet.addMergedRegion(new CellRangeAddress(5, 5, 21, 25));

        // FILA 7 (C - E)
        Row row7 = sheet.getRow(6);
        Cell anoMayorCell = row7.createCell(2);
        anoMayorCell.setCellValue(anoMayor);
        anoMayorCell.setCellStyle(createHeaderStyle(workbook));
        sheet.addMergedRegion(new CellRangeAddress(6, 6, 2, 4));

        // FILA 8 (C - E)
        Row row8 = sheet.getRow(7);
        Cell anoCell = row8.createCell(2);
        anoCell.setCellValue("AÑOS");
        anoCell.setCellStyle(createHeaderStyle(workbook));
        sheet.addMergedRegion(new CellRangeAddress(7, 7, 2, 4));

        // FILA 7 Y 8 (V - Z)
        sheet.addMergedRegion(new CellRangeAddress(6, 7, 21, 25));

        // FILA 6, 7 Y 8 (F - U)
        Cell academiaCell = row6.createCell(5);
        academiaCell.setCellValue(equipo.getAcademia().getNombreAcademia());
        academiaCell.setCellStyle(createAcademiaStyle(workbook));
        sheet.addMergedRegion(new CellRangeAddress(5, 7, 5, 20));

        // FILA 9 (A - E)
        Row row9 = sheet.getRow(8);
        Cell catCell = row9.createCell(0);
        catCell.setCellValue("CATEGORIA");
        catCell.setCellStyle(createHoraStyle(workbook));
        sheet.addMergedRegion(new CellRangeAddress(8, 8, 0, 4));

        // (F - U)
        sheet.addMergedRegion(new CellRangeAddress(8, 8, 5, 20));

        // (V - Z)
        Cell fechaJuegoCell = row9.createCell(21);
        fechaJuegoCell.setCellValue("FECHA DE JUEGO");
        fechaJuegoCell.setCellStyle(createHoraStyle(workbook));
        sheet.addMergedRegion(new CellRangeAddress(8, 8, 21, 25));

        // FILA 10
        Row row10 = sheet.getRow(9);
        row10.setHeightInPoints(30);

        // A - Z
        Cell planillaCell = row10.createCell(0);
        planillaCell.setCellValue("PLANILLA DE JUEGO");
        planillaCell.setCellStyle(createPlanillaStyle(workbook));
        sheet.addMergedRegion(new CellRangeAddress(9, 9, 0, 25));

        // FILA 11
        Row row11 = sheet.getRow(10);
        row11.setHeightInPoints(27);

        // A - I
        Cell dt_cell = row11.createCell(0);
        dt_cell.setCellValue(equipo.getEntrenador().getApellidos().toUpperCase() + " " + equipo.getEntrenador().getNombres().toUpperCase());
        dt_cell.setCellStyle(createHoraStyle(workbook));
        sheet.addMergedRegion(new CellRangeAddress(10, 10, 0, 8));

        // J - R
        Cell at_cell = row11.createCell(9);
        at_cell.setCellValue(equipo.getDelegado().getApellidos().toUpperCase() + " " + equipo.getDelegado().getNombres().toUpperCase());
        at_cell.setCellStyle(createHoraStyle(workbook));
        sheet.addMergedRegion(new CellRangeAddress(10, 10, 9, 17));

        // S - Z
        sheet.addMergedRegion(new CellRangeAddress(10, 10, 18, 25));

        // FILA 12
        Row row12 = sheet.getRow(11);

        // A - I
        Cell directorCell = row12.createCell(0);
        directorCell.setCellValue("DIRECTOR TÉCNICO");
        directorCell.setCellStyle(createHoraStyle(workbook)); // Reutilizamos el estilo
        sheet.addMergedRegion(new CellRangeAddress(11, 11, 0, 8));

        // J - R
        Cell asistenteCell = row12.createCell(9);
        asistenteCell.setCellValue("ASISTENTE TÉCNICO");
        asistenteCell.setCellStyle(createHoraStyle(workbook));
        sheet.addMergedRegion(new CellRangeAddress(11, 11, 9, 17));

        // S - Z
        Cell delegadoCell = row12.createCell(18);
        delegadoCell.setCellValue("DELEGADO DE MESA");
        delegadoCell.setCellStyle(createHoraStyle(workbook));
        sheet.addMergedRegion(new CellRangeAddress(11, 11, 18, 25));

        // FILA 13 - 16
        Row row13 = sheet.getRow(12);
        row13.setHeightInPoints(25);
        Row row14 = sheet.getRow(13);
        row14.setHeightInPoints(25);
        Row row15 = sheet.getRow(14);
        row15.setHeightInPoints(25);
        Row row16 = sheet.getRow(15);
        row16.setHeightInPoints(25);

        // A (columnas 0) = N° ORDEN (vertical)
        Cell ordenCell = row13.createCell(0);
        ordenCell.setCellValue("N° ORDEN");
        ordenCell.setCellStyle(createVerticalStyle(workbook));
        sheet.addMergedRegion(new CellRangeAddress(12, 15, 0, 0));

        // B (columna 1) = CAMISETA (vertical)
        Cell camisetaCell = row13.createCell(1);
        camisetaCell.setCellValue("CAMISETA");
        camisetaCell.setCellStyle(createVerticalStyle(workbook));
        sheet.addMergedRegion(new CellRangeAddress(12, 15, 1, 1));

        // C - E
        Cell dniCell = row13.createCell(2);
        dniCell.setCellValue("N° DNI");
        dniCell.setCellStyle(createHoraStyle(workbook));
        sheet.addMergedRegion(new CellRangeAddress(12, 15, 2, 4));

        // F - P
        Cell nombresCell = row13.createCell(5);
        nombresCell.setCellValue("APELLIDOS Y NOMBRES\n(MAYUSCULAS)");
        nombresCell.setCellStyle(createHoraStyle(workbook));
        sheet.addMergedRegion(new CellRangeAddress(12, 15, 5, 15));

        // Q
        Cell edadCell = row13.createCell(16);
        edadCell.setCellValue("EDAD");
        edadCell.setCellStyle(createVerticalStyle(workbook));
        sheet.addMergedRegion(new CellRangeAddress(12, 15, 16, 16));

        // R - S
        Cell ano_Cell = row13.createCell(17);
        ano_Cell.setCellValue("AÑO\nNAC.");
        ano_Cell.setCellStyle(createHoraStyle(workbook));
        sheet.addMergedRegion(new CellRangeAddress(12, 15, 17, 18));

        // T
        Cell habilitadoCell = row13.createCell(19);
        habilitadoCell.setCellValue("HABILITADO");
        habilitadoCell.setCellStyle(createVerticalStyle(workbook));
        sheet.addMergedRegion(new CellRangeAddress(12, 15, 19, 19));

        // U
        Cell titularCell = row13.createCell(20);
        titularCell.setCellValue("TITULAR");
        titularCell.setCellStyle(createVerticalStyle(workbook));
        sheet.addMergedRegion(new CellRangeAddress(12, 14, 20, 20));

        Cell tCell = row16.createCell(20);
        tCell.setCellValue("T");
        tCell.setCellStyle(createHoraStyle(workbook));

        // V
        Cell cambioCell = row13.createCell(21);
        cambioCell.setCellValue("CAMBIO");
        cambioCell.setCellStyle(createVerticalStyle(workbook));
        sheet.addMergedRegion(new CellRangeAddress(12, 14, 21, 21));

        Cell cCell = row16.createCell(21);
        cCell.setCellValue("C");
        cCell.setCellStyle(createHoraStyle(workbook));

        // W
        Cell reingresoCell = row13.createCell(22);
        reingresoCell.setCellValue("REINGRESO");
        reingresoCell.setCellStyle(createVerticalStyle(workbook));
        sheet.addMergedRegion(new CellRangeAddress(12, 14, 22, 22));

        Cell rCell = row16.createCell(22);
        rCell.setCellValue("R");
        rCell.setCellStyle(createHoraStyle(workbook));

        // X
        Cell asistenciaCell = row13.createCell(23);
        asistenciaCell.setCellValue("ASISTENCIA");
        asistenciaCell.setCellStyle(createVerticalStyle(workbook));
        sheet.addMergedRegion(new CellRangeAddress(12, 14, 23, 23));

        Cell unoCell = row16.createCell(23);
        unoCell.setCellValue("1");
        unoCell.setCellStyle(createHoraStyle(workbook));

        // Y
        Cell activadoCell = row13.createCell(24);
        activadoCell.setCellValue("ACTIVADO");
        activadoCell.setCellStyle(createVerticalStyle(workbook));
        sheet.addMergedRegion(new CellRangeAddress(12, 14, 24, 24));

        Cell aCell = row16.createCell(24);
        aCell.setCellValue("A");
        aCell.setCellStyle(createHoraStyle(workbook));

        // Z
        Cell golesCell = row13.createCell(25);
        golesCell.setCellValue("GOLES");
        golesCell.setCellStyle(createVerticalStyle(workbook));
        sheet.addMergedRegion(new CellRangeAddress(12, 15, 25, 25));


        int totalJugadoresHabilitados = 0;

        // FILA 17 - 31
        for (int i = 0; i < 15; i++) {
            Row row = sheet.getRow(16 + i);

            // Columna A
            Cell orden_Cell = row.createCell(0);
            orden_Cell.setCellValue(String.format("%02d", i + 1));
            orden_Cell.setCellStyle(createHoraStyle(workbook));

            if (i < equipo.getJugadores().size()) {
                Jugador jugador = equipo.getJugadores().get(i);

                // Columna B - CAMISETA
                Cell camiseta_Cell = row.createCell(1);
                //camisetaCell.setCellValue(jugador.getNumeroCamiseta());
                camiseta_Cell.setCellStyle(createHoraStyle(workbook));

                // Columnas C-E - N° DNI
                Cell dni_Cell = row.createCell(2);
                dni_Cell.setCellValue(jugador.getDni());
                dni_Cell.setCellStyle(createHoraStyle(workbook));
                sheet.addMergedRegion(new CellRangeAddress(16 + i, 16 + i, 2, 4));

                // Columnas F-P - APELLIDOS Y NOMBRES
                Cell nombres_Cell = row.createCell(5);
                nombres_Cell.setCellValue(jugador.getApellidos().toUpperCase() + " " + jugador.getNombres().toUpperCase());
                nombres_Cell.setCellStyle(createLeftAlignedStyle(workbook));
                sheet.addMergedRegion(new CellRangeAddress(16 + i, 16 + i, 5, 15));

                // Columna Q - EDAD
                Cell edad_Cell = row.createCell(16);
                edad_Cell.setCellValue(calcularEdad(jugador.getFechaNacimiento()));
                edad_Cell.setCellStyle(createHoraStyle(workbook));

                // Columnas R-S - AÑO NACIMIENTO
                Cell anoNacCell = row.createCell(17);
                anoNacCell.setCellValue(jugador.getFechaNacimiento().getYear());
                anoNacCell.setCellStyle(createHoraStyle(workbook));
                sheet.addMergedRegion(new CellRangeAddress(16 + i, 16 + i, 17, 18));

                // Columna T - HABILITADO
                Cell habilitado_Cell = row.createCell(19);
                habilitado_Cell.setCellValue("H");
                habilitado_Cell.setCellStyle(createHoraStyle(workbook));

                totalJugadoresHabilitados++;

            } else {
                Cell camiseta_Cell = row.createCell(1);
                camiseta_Cell.setCellStyle(createHoraStyle(workbook));

                Cell dni_Cell = row.createCell(2);
                dni_Cell.setCellStyle(createHoraStyle(workbook));
                sheet.addMergedRegion(new CellRangeAddress(16 + i, 16 + i, 2, 4));

                Cell nombres_Cell = row.createCell(5);
                nombres_Cell.setCellStyle(createHoraStyle(workbook));
                sheet.addMergedRegion(new CellRangeAddress(16 + i, 16 + i, 5, 15));

                Cell edad_Cell = row.createCell(16);
                edad_Cell.setCellStyle(createHoraStyle(workbook));

                Cell anoNacCell = row.createCell(17);
                anoNacCell.setCellStyle(createHoraStyle(workbook));
                sheet.addMergedRegion(new CellRangeAddress(16 + i, 16 + i, 17, 18));

                // Columna T - HABILITADO (N con fondo rojo)
                Cell habilitado_Cell = row.createCell(19);
                habilitado_Cell.setCellValue("N");
                habilitado_Cell.setCellStyle(createErrorStyle(workbook));
            }
        }

        // FILA 32
        Row row32 = sheet.getRow(31);
        Cell resumenCell = row32.createCell(0);
        resumenCell.setCellValue("RESUMEN");
        resumenCell.setCellStyle(createHeaderStyle(workbook));
        sheet.addMergedRegion(new CellRangeAddress(31, 31, 0, 25));

        // FILA 33 - 36 (espacio para el score en números)
        sheet.addMergedRegion(new CellRangeAddress(32, 35, 10, 15));

        // K - P (espacio para el scord en letras)
        sheet.addMergedRegion(new CellRangeAddress(36, 36, 10, 15));

        // FILA 33 - 34 (B - I)
        CellStyle styleNoBorders = createStyleWithoutBorder(workbook);

        for (int rowIdx = 32; rowIdx <= 33; rowIdx++) {
            Row row = sheet.getRow(rowIdx);
            if (row == null) row = sheet.createRow(rowIdx);

            for (int colIdx = 1; colIdx <= 8; colIdx++) {
                Cell cell = row.getCell(colIdx);
                if (cell == null) cell = row.createCell(colIdx);
                cell.setCellStyle(styleNoBorders);
            }
        }
        sheet.addMergedRegion(new CellRangeAddress(32, 33, 1, 8));

        // FILA 33 - 34
        Row row33 = sheet.getRow(32);
        row33.setHeightInPoints(10);

        // FILA 33 - 38

        CellStyle styleWithoutRightBorder = createStyleWithoutRightBorder(workbook);
        CellStyle styleWithoutLeftBorder = createStyleWithoutLeftBorder(workbook);

        for (int i = 32; i <= 37; i++) {
            Row row = sheet.getRow(i);
            if (row == null) row = sheet.createRow(i);

            Cell cellA = row.createCell(0);
            cellA.setCellStyle(styleWithoutRightBorder);

            Cell cellJ = row.createCell(9);
            cellJ.setCellStyle(styleWithoutLeftBorder);
        }

        // A
        sheet.addMergedRegion(new CellRangeAddress(32, 37, 0, 0));

        // J
        sheet.addMergedRegion(new CellRangeAddress(32, 37, 9, 9));

        // Columna T (índice 19)
        Cell totalHabilitadosCell = row33.createCell(19);
        totalHabilitadosCell.setCellValue(totalJugadoresHabilitados);
        totalHabilitadosCell.setCellStyle(createHoraStyle(workbook));
        sheet.addMergedRegion(new CellRangeAddress(32, 33, 19, 19));

        // Columna U (índice 20)
        sheet.addMergedRegion(new CellRangeAddress(32, 33, 20, 20));

        // Columna V (índice 21)
        sheet.addMergedRegion(new CellRangeAddress(32, 33, 21, 21));

        // Columna W (índice 22)
        sheet.addMergedRegion(new CellRangeAddress(32, 33, 22, 22));

        // Columna X (índice 23)
        sheet.addMergedRegion(new CellRangeAddress(32, 33, 23, 23));

        // Columna Y (índice 24)
        sheet.addMergedRegion(new CellRangeAddress(32, 33, 24, 24));

        // Columna Z (índice 25)
        sheet.addMergedRegion(new CellRangeAddress(32, 33, 25, 25));

        sheet.addMergedRegion(new CellRangeAddress(32, 37, 16, 18));

        // FILA 35
        // T - Z
        Row row35 = sheet.getRow(34);
        Cell resumeCell = row35.createCell(19);
        resumeCell.setCellValue("RESUMEN TOTAL");
        resumeCell.setCellStyle(createHoraStyle(workbook));
        sheet.addMergedRegion(new CellRangeAddress(34, 34, 19, 25));

        // B - I
        CellStyle styleOnlyTopBorder = createStyleOnlyTopBorder(workbook);

        for (int colIdx = 1; colIdx <= 8; colIdx++) {
            Cell cell = row35.getCell(colIdx);
            if (cell == null) {
                cell = row35.createCell(colIdx);
            }
            cell.setCellStyle(styleOnlyTopBorder);
        }

        Cell FirmaEntrenadorCell = row35.createCell(1);
        FirmaEntrenadorCell.setCellValue("FIRMA DEL ENTRENADOR");
        FirmaEntrenadorCell.setCellStyle(styleOnlyTopBorder);
        sheet.addMergedRegion(new CellRangeAddress(34, 34, 1, 8));

        // FILA 36
        Row row36 = sheet.getRow(35);

        // B - I
        CellStyle styleWithoutBorder = createStyleWithoutBorder(workbook);

        for (int rowIdx = 35; rowIdx <= 36; rowIdx++) {
            Row row = sheet.getRow(rowIdx);
            if (row == null) row = sheet.createRow(rowIdx);

            for (int colIdx = 1; colIdx <= 8; colIdx++) {
                Cell cell = row.getCell(colIdx);
                if (cell == null) cell = row.createCell(colIdx);
                cell.setCellStyle(styleWithoutBorder);
            }
        }

        sheet.addMergedRegion(new CellRangeAddress(35, 36, 1, 8));

        // T - W
        Cell arbitrajeCell = row36.createCell(19);
        arbitrajeCell.setCellValue("ARBITRAJE");
        arbitrajeCell.setCellStyle(createHoraStyle(workbook));
        sheet.addMergedRegion(new CellRangeAddress(35, 35, 19, 22));

        // X - Z
        Cell dni_Cell = row36.createCell(23);
        dni_Cell.setCellValue("DNI");
        dni_Cell.setCellStyle(createHoraStyle(workbook));
        sheet.addMergedRegion(new CellRangeAddress(35, 35, 23, 25));

        // FILA 37 - 38

        // T - W
        sheet.addMergedRegion(new CellRangeAddress(36, 37, 19, 22));

        // X - Z
        sheet.addMergedRegion(new CellRangeAddress(36, 37, 23, 25));

        // FILA 38
        Row row38 = sheet.getRow(37);

        // K - P
        Cell scordCell = row38.createCell(10);
        scordCell.setCellValue("SCORD FINAL");
        scordCell.setCellStyle(createHoraStyle(workbook));
        sheet.addMergedRegion(new CellRangeAddress(37, 37, 10, 15));

        // B - I
        CellStyle styleTopBorderAndBottomBorder = createStyleTopBorderAndBottomBorder(workbook);

        for (int colIdx = 1; colIdx <= 8; colIdx++) {
            Cell cell = row38.getCell(colIdx);
            if (cell == null) {
                cell = row38.createCell(colIdx);
            }
            cell.setCellStyle(styleTopBorderAndBottomBorder);
        }

        Cell FirmaArbitroCell = row38.createCell(1);
        FirmaArbitroCell.setCellValue("FIRMA DEL ARBITRO");
        FirmaArbitroCell.setCellStyle(styleTopBorderAndBottomBorder);
        sheet.addMergedRegion(new CellRangeAddress(37, 37, 1, 8));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        workbook.write(out);
        workbook.close();

        return out.toByteArray();
    }

    private CellStyle createDefaultBorderStyle(XSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }

    private CellStyle createTitleStyle(XSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial Black");
        font.setBold(true);
        font.setFontHeightInPoints((short) 28);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        return style;
    }

    private CellStyle createLigaStyle(XSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Calibri");
        font.setBold(true);
        font.setFontHeightInPoints((short) 26);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        return style;
    }

    private CellStyle createHeaderStyle(XSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Calibri");
        font.setBold(true);
        font.setFontHeightInPoints((short) 16);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        return style;
    }

    private CellStyle createCategoriaStyle(XSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Calibri");
        font.setBold(true);
        font.setFontHeightInPoints((short) 36);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        return style;
    }

    private CellStyle createAcademiaStyle(XSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();

        XSSFFont font = workbook.createFont();
        font.setFontName("Calibri");
        font.setBold(true);
        font.setFontHeightInPoints((short) 20);

        // Color #3333ff
        font.setColor(new XSSFColor(new byte[]{(byte) 0x33, (byte) 0x33, (byte) 0xff}, null));

        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);

        return style;
    }

    private CellStyle createHoraStyle(XSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Calibri");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        return style;
    }

    private CellStyle createPlanillaStyle(XSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Calibri");
        font.setBold(true);
        font.setFontHeightInPoints((short) 25);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        return style;
    }

    private CellStyle createVerticalStyle(XSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Calibri");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setRotation((short) 90);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        return style;
    }

    private CellStyle createLeftAlignedStyle(XSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Calibri");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14);
        style.setFont(font);
        style.setAlignment(HorizontalAlignment.LEFT);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        return style;
    }

    private CellStyle createErrorStyle(XSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();

        Font font = workbook.createFont();
        font.setFontName("Calibri");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14);
        font.setColor(IndexedColors.WHITE.getIndex());

        style.setFont(font);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setFillForegroundColor(IndexedColors.RED.getIndex());
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);

        return style;
    }

    private CellStyle createStyleWithoutRightBorder(XSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.NONE);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }

    private CellStyle createStyleWithoutLeftBorder(XSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.NONE);
        style.setBorderRight(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        return style;
    }

    private CellStyle createStyleWithoutBorder(XSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setBorderTop(BorderStyle.NONE);
        style.setBorderBottom(BorderStyle.NONE);
        style.setBorderLeft(BorderStyle.NONE);
        style.setBorderRight(BorderStyle.NONE);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        Font font = workbook.createFont();
        font.setBold(true);
        font.setFontHeightInPoints((short) 11);
        style.setFont(font);
        return style;
    }

    private CellStyle createStyleTopBorderAndBottomBorder(XSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.NONE);
        style.setBorderRight(BorderStyle.NONE);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        Font font = workbook.createFont();
        font.setFontName("Calibri");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14);
        style.setFont(font);
        return style;
    }

    private CellStyle createStyleOnlyTopBorder(XSSFWorkbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderBottom(BorderStyle.NONE);
        style.setBorderLeft(BorderStyle.NONE);
        style.setBorderRight(BorderStyle.NONE);
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        Font font = workbook.createFont();
        font.setFontName("Calibri");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14);
        style.setFont(font);
        return style;
    }

    private int calcularEdad(LocalDate fechaNacimiento) {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    private int extraerNumeroCategoria(String categoria) {
        String numeroStr = categoria.replaceAll("[^0-9]", "");
        return Integer.parseInt(numeroStr);
    }
}