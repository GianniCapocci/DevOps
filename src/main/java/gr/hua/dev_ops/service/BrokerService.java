package gr.hua.dev_ops.service;

import gr.hua.dev_ops.entity.Broker;
import org.springframework.stereotype.Service;

@Service
public interface BrokerService {
    Broker findById(Long id);
}
