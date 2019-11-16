package app.repository;

import app.domain.entities.Channel;

public interface ChannelRepository extends GenericRepository<Channel, String > {
    boolean deleteChannel(String id);
}
