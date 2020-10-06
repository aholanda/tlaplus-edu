LATEX := xelatex

main.pdf: main.tex intro.tex mdc.tex tlaplus.tex
	$(LATEX) $<

.PHONY: clean

clean:
	$(RM) *.aux *.log *.nav *.out main.pdf *.snm *.toc *.vrb
