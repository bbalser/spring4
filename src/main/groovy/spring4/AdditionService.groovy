package spring4

import org.springframework.stereotype.Service

@Service
class AdditionService {

    def add(numbers) {
        numbers.sum()
    }

}
