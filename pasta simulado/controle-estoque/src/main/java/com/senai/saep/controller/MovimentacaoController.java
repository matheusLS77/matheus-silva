package com.senai.saep.controller;

import com.senai.saep.dto.MovimentacaoDto;
import com.senai.saep.enums.TipoMovimentacao;
import com.senai.saep.service.MaterialService;
import com.senai.saep.service.MovimentacaoService;
import com.senai.saep.sessao.SessaoDto;
import com.senai.saep.sessao.SessaoUtil;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MovimentacaoController {
    private final MovimentacaoService movimentacaoService;
    private final MaterialService materialService;

    public MovimentacaoController(MovimentacaoService movimentacaoService, MaterialService materialService) {
        this.movimentacaoService = movimentacaoService;
        this.materialService = materialService;
    }

    @PostMapping("/movimentacao")
    public String cadastrarMovimentacao(@Valid @ModelAttribute("movimentacao") MovimentacaoDto movimentacaoDto, BindingResult bindingResult,
                                        RedirectAttributes redirectAttributes, HttpSession session, Model model) {

        SessaoDto usuario = SessaoUtil.obterSessao(session);

        if (usuario == null) {
            return "redirect:/login";
        }

        movimentacaoDto.setIdUsuario(usuario.getUsuarioId());

        model.addAttribute("usuarioLogin", usuario.getUsuarioLogin());

        if (bindingResult.hasErrors()) {
            model.addAttribute("movimentacoes", movimentacaoService.listar());
            model.addAttribute("tipos", TipoMovimentacao.values());
            model.addAttribute("materiais", materialService.listar());
            return "movimentacaocadastrar";
        }

        try {
            movimentacaoService.cadastrar(movimentacaoDto);
        } catch (IllegalArgumentException e) {
            model.addAttribute("erro", e.getMessage());
            model.addAttribute("movimentacoes", movimentacaoService.listar());
            model.addAttribute("materiais", materialService.listar());
            model.addAttribute("tipos", TipoMovimentacao.values());

            return "movimentacaocadastrar";
        }

        redirectAttributes.addFlashAttribute("mensagem", "Movimentação realizada com sucesso ");

        return "redirect:/movimentacaolista";
    }

}
