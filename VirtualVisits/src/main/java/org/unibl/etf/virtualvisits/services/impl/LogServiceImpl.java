package org.unibl.etf.virtualvisits.services.impl;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.unibl.etf.virtualvisits.models.Log;
import org.unibl.etf.virtualvisits.models.entities.LogEntity;
import org.unibl.etf.virtualvisits.repositories.LogEntityRepository;
import org.unibl.etf.virtualvisits.services.LogService;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogServiceImpl implements LogService {

    private final LogEntityRepository logEntityRepository;

    private final ModelMapper modelMapper;

    public LogServiceImpl(LogEntityRepository logEntityRepository, ModelMapper modelMapper) {
        this.logEntityRepository = logEntityRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void insert(LogEntity log) {
        logEntityRepository.save(log);
    }

    @Override
    public List<Log> findAll() {
        return logEntityRepository.findAll().stream().map(m->modelMapper.map(m, Log.class)).collect(Collectors.toList());
    }

    @Override
    public byte[] getPdf() {
        List<Log> logs=findAll();
        ByteArrayOutputStream outputStream = null;

        //writing pdf to the output stream
        outputStream = new ByteArrayOutputStream();
        writePdf(outputStream, logs);
        byte[] bytes = outputStream.toByteArray();

        return bytes;
    }

    private void writePdf(ByteArrayOutputStream outputStream, List<Log> logs){
        SimpleDateFormat sdf=new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
        document.open();
        Paragraph p=null;
        for(Log l : logs){
            p = new Paragraph(sdf.format(Date.from(l.getDateTime()))+" "+l.getInfo());
            document.add(p);
        }

        document.close();
    }
}
