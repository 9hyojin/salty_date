package com.my.salty_date.service;

import com.my.salty_date.dto.DatingDto;
import com.my.salty_date.entity.Dating;
import com.my.salty_date.entity.File;
import com.my.salty_date.repository.DatingRepository;
import com.my.salty_date.repository.FileRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Service
@RequiredArgsConstructor
public class DatingService {

    private final DatingRepository datingRepository;

    private final FileRepository fileRepository;




    public void save(DatingDto datingDto) throws IOException {
        Dating dating = Dating.toSaveDating(datingDto); // dto -> entity
        Long saveId = datingRepository.save(dating).getDatingIdx(); //6.    getId 하는 이유: 자식테이블에서는 부모의 PK가 필요함. (Long타입 아닌 DateEntity타입)
        Dating dating1 = datingRepository.findById(saveId).get();//부모 엔티티를 db에서 가져옴
        for (MultipartFile files : datingDto.getFile()) {
            String originalFilename = files.getOriginalFilename(); // 2.
            String storedFileName = System.currentTimeMillis() + "_" + originalFilename; // 3.
            String savePath = "/Users/koo/springboot_img/" + storedFileName; // 4. C:/springboot_img/9802398403948_내사진.jpg
            files.transferTo(new java.io.File(savePath)); // 5.
            File file = File.toFile(dating1, originalFilename, storedFileName);
            fileRepository.save(file);
        }
    }



    @Transactional
    public List<DatingDto> findAll(){
        List<Dating> datingList = datingRepository.findAll();
        List<DatingDto> datingDtoList = new ArrayList<>();
        for (Dating dating : datingList){
            datingDtoList.add(DatingDto.toDatingDto(dating));
        }
        return datingDtoList;
    }

    @Transactional
    public DatingDto findById(Long datingIdx){
        Optional<Dating> optionalDating = datingRepository.findById(datingIdx);
        if (optionalDating.isPresent()){
            Dating dating = optionalDating.get();
            DatingDto datingDto = DatingDto.toDatingDto(dating);
            return datingDto;
        }else{
            return null;
        }
    }

    public DatingDto update(DatingDto datingDto) {
        Dating dating = Dating.toUpdateDating(datingDto);
        datingRepository.save(dating);
        return findById(datingDto.getDatingIdx());
    }

    public void delete(Long datingIdx) {
        datingRepository.deleteById(datingIdx);
    }



}
