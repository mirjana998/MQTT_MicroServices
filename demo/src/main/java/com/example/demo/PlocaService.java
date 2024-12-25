package com.example.demo;

import com.example.demo.PlocaDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PlocaService {

    @Autowired
    private PlocaRepository plocaRepository;

    public Integer save(PlocaDTO vO) {
        System.out.println(vO.getId());
        System.out.println(vO.getOblikovana());
        System.out.println(vO.getLakirana());
        System.out.println(vO.getRavna());
        Ploca bean = new Ploca();
        BeanUtils.copyProperties(vO, bean);
        bean = plocaRepository.save(bean);
        return bean.getId();
    }

    public List<Ploca> all(){
     return   plocaRepository.findAll();
    }

    public Ploca id(Integer id){
      Optional<Ploca> p  = plocaRepository.findById(id);
      if (p.isPresent()){
          return p.get();
      }
      return null;
    }
}
