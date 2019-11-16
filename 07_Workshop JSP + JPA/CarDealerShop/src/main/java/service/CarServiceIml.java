package service;

import domain.entity.Car;
import domain.model.service.CarServiceModel;
import org.modelmapper.ModelMapper;
import repository.CarRepository;

import javax.inject.Inject;

public class CarServiceIml implements CarService {
    private final CarRepository carRepository;
    private final ModelMapper  modelMapper;

    @Inject
    public CarServiceIml(CarRepository carRepository, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean saveCar(CarServiceModel carServiceModel) {
      return carRepository.save(this.modelMapper.map(carServiceModel, Car.class));
    }
}
