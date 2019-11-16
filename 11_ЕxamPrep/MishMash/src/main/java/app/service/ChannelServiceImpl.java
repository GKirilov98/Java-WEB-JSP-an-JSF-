package app.service;

import app.domain.entities.Channel;
import app.domain.entities.Type;
import app.domain.models.service.ChannelServiceModel;
import app.repository.ChannelRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class ChannelServiceImpl implements ChannelService {
    private final ChannelRepository chanelRepository;
    private final ModelMapper modelMapper;

    @Inject
    public ChannelServiceImpl(ChannelRepository chanelRepository, ModelMapper modelMapper) {
        this.chanelRepository = chanelRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public boolean registerChannel(ChannelServiceModel channelServiceModel) {
        Channel channel = this.modelMapper.map(channelServiceModel, Channel.class);
        channel.setType(Type.valueOf(channelServiceModel.getType()));
        return this.chanelRepository.save(channel) != null;
    }

    @Override
    public ChannelServiceModel getChannelById(String id) {
        Channel channel = this.chanelRepository.findById(id);
        if (channel == null) {
            return null;
        }

        return this.modelMapper.map(channel, ChannelServiceModel.class);
    }

    @Override
    public List<ChannelServiceModel> findAll() {

        return this.chanelRepository.findAll()
                .stream()
                .map(u -> this.modelMapper.map(u, ChannelServiceModel.class))
                .collect(Collectors.toList());
    }

    @Override
    public boolean deleteChannel(String id) {
       return this.chanelRepository.deleteChannel(id);
    }
}
