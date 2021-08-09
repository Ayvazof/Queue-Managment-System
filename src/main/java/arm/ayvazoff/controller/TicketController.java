package arm.ayvazoff.controller;

import arm.ayvazoff.domain.State;
import arm.ayvazoff.domain.Ticket;
import arm.ayvazoff.repository.TicketRepository;
import arm.ayvazoff.repository.UserRepository;
import arm.ayvazoff.service.TicketService;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    @GetMapping("/next")
    @ApiOperation(value = "Returns next not processed ticket in queue",
    notes = "Change ticket state from 'wait' state to 'process'",
    response = Ticket.class)
    public Ticket next(@RequestParam("userId") String userId) {
        try {
            Ticket nextTicket = ticketRepository.getNext();
            nextTicket.setUser(userRepository.getOne(userId));
            return ticketService.changeState(nextTicket, State.PROCESS);

        } catch (NullPointerException n) { //what I should do
            return null;
        }
    }

    @PutMapping("/process")
    @ApiOperation(value = "Change ticket state (DONE, PROCESS, WAIT, PASSED)",
            notes = "Provide ticket id, state and user id to update ticket state",
            response = Ticket.class)
    public Ticket process(@RequestParam("state") State state,
                          @RequestParam("ticketId") int ticketId,
                          @RequestParam("userId") String userId) {

        Ticket ticket = ticketService.changeState(ticketRepository.getOne(ticketId), state);
        ticket.setUser(userRepository.getOne(userId));

        return ticket;
    }

    @PutMapping("/process/done")
    @ApiOperation(value = "Change ticket state to DONE",
            notes = "Provide ticket and user id to update ticket state",
            response = Ticket.class)
    public Ticket processDone(@RequestParam("ticketId") int ticketId,
                          @RequestParam("userId") String userId) {

        Ticket ticket = ticketService.changeState(ticketRepository.getOne(ticketId), State.DONE);
        ticket.setUser(userRepository.getOne(userId));

        return ticket;
    }

    @PutMapping("/process/pass")
    @ApiOperation(value = "Change ticket state to PASSED",
            notes = "Provide ticket and user id to update ticket state",
            response = Ticket.class)
    public Ticket processPass(@RequestParam("ticketId") int ticketId,
                              @RequestParam("userId") String userId) {

        Ticket ticket = ticketService.changeState(ticketRepository.getOne(ticketId), State.PASSED);
        ticket.setUser(userRepository.getOne(userId));

        return ticket;
    }

    @PostMapping
    public void add(@RequestParam("task_id") String taskId) {
        ticketService.addTicket(taskId);
    }

    @PutMapping("{id}")
    public void update(@PathVariable("id") Ticket ticketFromDb,
                       @RequestBody Ticket ticket) {

        BeanUtils.copyProperties(ticket, ticketFromDb, "id");
        ticketRepository.save(ticket);
    }
}
