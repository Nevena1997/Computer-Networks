# .tex file must have same name as the folder

# MAINFILE = $(shell basename `pwd`)
MAINFILE = RM4I_2021_SEP1

OBJECTS = $(shell echo *.tex)
STYLES = $(shell echo *.sty)
BIB = $(shell echo *.bib)

OBJECTS_TEST = $(addsuffix .t, $(basename $(OBJECTS)))
TESTS = $(addprefix make/, $(OBJECTS_TEST))
TEMP2 := $(shell mkdir make 2>/dev/null)

.PHONY: all
all: $(MAINFILE).pdf

$(MAINFILE).pdf: $(TESTS)
	pdflatex $(MAINFILE)
	pdflatex $(MAINFILE)

make/%.t: %.tex
	touch $@

.PHONY: clean
clean:
	-rm -f *.aux
	-rm -f *.log
	-rm -f *.toc
	-rm -f *.bbl
	-rm -f *.blg
	-rm -f *.out
	-rm -f make/bib

.PHONY: cleanall
cleanall: clean
	-rm -f *.pdf
	-rm -f *.ps
	-rm -f *.dvi
	-rm -rf ./make
