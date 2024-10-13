package sn.sae.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sn.sae.entity.CompressionUtil;
import sn.sae.entity.DecompressionUtil;
import sn.sae.entity.Document;
import sn.sae.repository.DocumentRepository;
import java.io.IOException;
import java.time.LocalDate;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public Document saveDocument(MultipartFile file) throws IOException {
        Document document = new Document();
        document.setFilename(file.getOriginalFilename());
        byte[] compressedData = CompressionUtil.compressFile(file.getOriginalFilename(), file.getBytes());
        document.setFileData(compressedData);
        document.setContentType(file.getContentType());
        document.setDateUploaded(LocalDate.now());
        return documentRepository.save(document);
    }

    public Document getDocument(Long id) throws IOException {
        Document document = documentRepository.findById(id).orElse(null);
        if (document != null) {
            byte[] decompressedData = DecompressionUtil.decompressFile(document.getFileData());
            document.setFileData(decompressedData);
        }
        return document;
    }
}
