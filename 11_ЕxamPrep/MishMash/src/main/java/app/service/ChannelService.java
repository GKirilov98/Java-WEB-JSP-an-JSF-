package app.service;

import app.domain.models.service.ChannelServiceModel;

import java.util.List;

public interface ChannelService {
    boolean registerChannel(ChannelServiceModel channelServiceModel);
    ChannelServiceModel getChannelById(String id);

    List<ChannelServiceModel> findAll();

    boolean deleteChannel(String id);
}
