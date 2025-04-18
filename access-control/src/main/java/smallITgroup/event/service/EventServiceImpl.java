package smallITgroup.event.service;

import java.time.LocalDate;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import smallITgroup.client.dto.EventDto;
import smallITgroup.event.dao.EventRepository;
import smallITgroup.event.dto.ResponseDto;
import smallITgroup.event.model.Event;


@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {
	final EventRepository eventRepository;
	final ModelMapper modelMapper;

	@Override
	public Map<Long, EventDto> getNewEvents() {
		System.out.println("print from getNewEvents");

		return eventRepository.findAll().stream().filter(event -> event.getNewEvents())
				.collect(Collectors.toMap(Event::getId, event -> modelMapper.map(event, EventDto.class)));
	}

	@Override
	public Map<Long, EventDto> getDoorActivityById(String doorId) {
		System.out.println("print from getDoorActivityById");
		return null;
	}

	@Override
	public Map<Long, EventDto> getHistoryByDay(LocalDate date) {
		System.out.println("print from getHistoryByDay");
		return null;
	}

	@Override
	public ResponseDto saveNewEvent() {
		// TODO Auto-generated method stub
		return null;
	}

}
