package com.example.springboot.service;

import com.example.springboot.entity.Office;
import com.example.springboot.repository.OfficeRepository;
import com.example.springboot.request.OfficeRequest;
import com.example.springboot.response.OfficeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OfficeService {
    @Autowired
    OfficeRepository officeRepository;

    public void saveOffice(OfficeRequest officeRequest){
        Office office = new Office(officeRequest.getOfficeName(),officeRequest.getOfficePhoneNumber());
        officeRepository.save(office);
    }

    public List<OfficeResponse> getAllOffice(){
        List<OfficeResponse> officeResponses = new ArrayList<>();
        for (Office office : officeRepository.findAll()) {
            OfficeResponse response = new OfficeResponse(
                    office.getOfficeId(),
                    office.getOfficeName(),
                    office.getOfficePhoneNumber()

            );
            officeResponses.add(response);
        }
        return officeResponses;
    }

    public void deleteOffice(OfficeRequest officeRequest){
        Optional<Office> office = officeRepository.findOfficeByOfficeName(officeRequest.getOfficeName());
        if(office.isPresent()){
            officeRepository.delete(office.get());
        }
    }
}
