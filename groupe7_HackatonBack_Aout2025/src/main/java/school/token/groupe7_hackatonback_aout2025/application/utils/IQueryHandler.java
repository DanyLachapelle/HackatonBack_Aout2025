package school.token.groupe7_hackatonback_aout2025.application.utils;

import org.springframework.stereotype.Service;

@Service
public interface IQueryHandler<I, O> {
    O handle(I input);
}
