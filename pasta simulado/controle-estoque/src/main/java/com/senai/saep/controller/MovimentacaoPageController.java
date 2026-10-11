package com.senai.saep.controller;

import com.senai.saep.dto.MaterialDto;
import com.senai.saep.dto.MovimentacaoDto;
import com.senai.saep.enums.TipoMovimentacao;
import com.senai.saep.service.CategoriaService;
import com.senai.saep.service.MaterialService;
import com.senai.saep.service.MovimentacaoService;
import com.senai.saep.service.UsuarioService;
import com.senai.saep.sessao.SessaoDto;
import com.senai.saep.sessao.SessaoUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MovimentacaoPageController {
    private final MovimentacaoService movimentacaoService;
    private final UsuarioService usuarioService;
    private final MaterialService materialService;

    public MovimentacaoPageController(MovimentacaoService movimentacaoService, UsuarioService usuarioService, MaterialService materialService) {
        this.movimentacaoService = movimentacaoService;
        this.usuarioService = usuarioService;
        this.materialService = materialService;
    }

    @GetMapping("/movimentacaolista")
    public String movimentacaoLista(Model model, HttpSession session) {
        SessaoDto sessaoDto = SessaoUtil.obterSessao(session);

        if (sessaoDto == null) {
            return "redirect:/login";
        }

        model.addAttribute("usuarioLogin", sessaoDto.getUsuarioLogin());
        model.addAttribute("movimentacoes", movimentacaoService.listar());

        return "movimentacaolista";
    }

    @GetMapping("/movimentacaocadastrar")
    public String movimentacaoCadastrar(Model model, HttpSession session) {
        SessaoDto sessaoDto = SessaoUtil.obterSessao(session);

        if (sessaoDto == null) {
            return "redirect:/login";
        }

        MovimentacaoDto movimentacao = new MovimentacaoDto();
        movimentacao.setIdUsuario(sessaoDto.getUsuarioId());

        model.addAttribute("usuarioLogin", sessaoDto.getUsuarioLogin());
        model.addAttribute("materiais", materialService.listar());
        model.addAttribute("tipos", TipoMovimentacao.values());
        model.addAttribute("movimentacao", movimentacao);

        return "movimentacaocadastrar";
    }
}
