package kg.peaksoft.peaksoftlmsab4.api.controller;

import kg.peaksoft.peaksoftlmsab4.model.entity.GroupEntity;
import kg.peaksoft.peaksoftlmsab4.model.entity.StudentEntity;
import kg.peaksoft.peaksoftlmsab4.model.enums.StudyFormat;
import kg.peaksoft.peaksoftlmsab4.model.mapper.StudentEditMapper;
import kg.peaksoft.peaksoftlmsab4.repository.GroupRepository;
import kg.peaksoft.peaksoftlmsab4.repository.StudentRepository;
import kg.peaksoft.peaksoftlmsab4.service.StudentService;
import lombok.AllArgsConstructor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/import")
@AllArgsConstructor
public class ImportExcelController {

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;

    @PostMapping("/import/{groupId}")
    public void importExcelFile(@RequestParam("file") MultipartFile files,@PathVariable Long groupId){

    }
}
