package com.my.salty_date.service;

import com.my.salty_date.dto.DatingRequest;
import com.my.salty_date.dto.UpdateDatingRequest;
import com.my.salty_date.entity.Dating;
import com.my.salty_date.entity.File;
import com.my.salty_date.repository.DatingRepository;
import com.my.salty_date.repository.FileRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@Service
@RequiredArgsConstructor
public class DatingService {

    private final DatingRepository datingRepository;

    private final FileRepository fileRepository;


    @Transactional
    public void save(DatingRequest request) throws IOException {
        Dating dating = request.toDatingEntity(); // dto -> entity
        Long saveId = datingRepository.save(dating).getDatingIdx(); //  getId 하는 이유: 자식테이블에서는 부모의 PK가 필요함. (Long타입 아닌 DateEntity타입)
        Dating dating1 = datingRepository.findById(saveId).get();//부모 엔티티를 db에서 가져옴
        for (MultipartFile files : request.getFile()) {
            String originalFilename = files.getOriginalFilename(); // 2.
            String storedFileName = System.currentTimeMillis() + "_" + originalFilename; // 3.
            String savePath = "/Users/koo/springboot_img/" + storedFileName; // 4. C:/springboot_img/9802398403948_내사진.jpg
            files.transferTo(new java.io.File(savePath)); // 5.
            File file = new File(dating1,originalFilename,storedFileName);
            fileRepository.save(file);
        }
    }


    @Transactional
    public List<Dating> findAll(){
        return datingRepository.findAll();
    }


    @Transactional
    public Dating findById(Long dateIdx){
        return datingRepository.findById(dateIdx)
                .orElseThrow(()->new IllegalArgumentException("not found: "+ dateIdx));
    }


    @Transactional
    public Dating update(Long dateIdx, UpdateDatingRequest request){
        Dating dating = datingRepository.findById(dateIdx).orElseThrow(()->new IllegalArgumentException("not found: "+ dateIdx));
        dating.update(request.getDatingTitle(), request.getDatingAddress(), request.getDatingContent());
        return dating;
    }



    public void delete(Long datingIdx) {
        datingRepository.deleteById(datingIdx);
    }


}






//    @Transactional
//    public List<DatingDto> findAll(){
//        List<Dating> datingList = datingRepository.findAll();
//        List<DatingDto> datingDtoList = new ArrayList<>();
//        for (Dating dating : datingList){
//            datingDtoList.add(DatingDto.toDatingDto(dating));
//        }
//        return datingDtoList;
//    }


//    @Transactional
//    public DatingDto findById(Long datingIdx) {
//        Optional<Dating> optionalDating = datingRepository.findById(datingIdx);
//        if (optionalDating.isPresent()) {
//            Dating dating = optionalDating.get();
//            DatingDto datingDto = DatingDto.toDatingDto(dating);
//            return datingDto;
//        } else {
//            return null;
//        }
//    }

//    public DatingDto update(DatingDto datingDto) {
//        Dating dating = Dating.toUpdateDating(datingDto);
//        datingRepository.save(dating);
//        return findById(datingDto.getDatingIdx());
//    }