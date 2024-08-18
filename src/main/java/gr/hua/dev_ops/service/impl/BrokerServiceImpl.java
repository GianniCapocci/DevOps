package gr.hua.dev_ops.service.impl;

import gr.hua.dev_ops.entity.Broker;
import gr.hua.dev_ops.repository.BrokerRepository;
import gr.hua.dev_ops.service.BrokerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrokerServiceImpl implements BrokerService {

    @Autowired
    private BrokerRepository brokerRepository;

    @Override
    public Broker findById(Long id) {
        return brokerRepository.findById(id).orElse(null);
    }
}
