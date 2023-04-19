package org.koreait.restcontrollers.board;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.koreait.commons.CommonException;
import org.koreait.commons.JSONResult;
import org.koreait.controllers.boards.BoardForm;
import org.koreait.models.board.Board;
import org.koreait.models.board.BoardDao;
import org.koreait.models.board.BoardWriteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Log
@RestController
@RequestMapping("/api/boards")
@RequiredArgsConstructor
public class ApiBoardController {
    private final BoardDao boardDao;
    private final BoardWriteService service;


    @GetMapping
    public ResponseEntity<JSONResult<List<Board>>> boardList() {
        List<Board> list = boardDao.gets();
        JSONResult<List<Board>> jsonResult = new JSONResult<>();
        jsonResult.setSuccess(true);
        jsonResult.setData(list);

        return ResponseEntity.ok(jsonResult);
    }

    @GetMapping("/{id}")
    public Board board(@PathVariable Long id) {
        Board board = boardDao.get(id);

        return board;
    }

    @PostMapping("/write")
    public ResponseEntity<List<Board>> write(@RequestBody BoardForm boardForm) {
        /**
        boolean result = true;
        if (result) {
            throw new CommonException("게시글 등록 실패", HttpStatus.BAD_REQUEST);
        }
        */

        service.write(boardForm);

        //List<Board> boards = boardDao.gets();
        /**
        return ResponseEntity.status(HttpStatus.CREATED) // 201
                             .build();
         */


        return ResponseEntity.created(URI.create("/board/list")).build();
    }
    /**
    @ExceptionHandler(Exception.class)
    public ResponseEntity<JSONResult<Object>> errorHandler(Exception e) {

        // e -> CommonExcecption 클래스로 부터 만들어진 객체이면 - getStatus()
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR; // 500 - 기본 응답 코드

        if (e instanceof CommonException) {
            CommonException commonException = (CommonException)e;
            status = commonException.getStatus();
        }

        JSONResult<Object> jsonResult = new JSONResult<>();
        jsonResult.setSuccess(false);
        jsonResult.setMessage(e.getMessage());
        jsonResult.setStatus(status);

        return ResponseEntity.status(status).body(jsonResult);
    }
    */
}
