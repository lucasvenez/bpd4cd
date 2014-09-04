# This script aims to build a graph to represent the grow of publications involving
# Business Process Management and Cloud computing between 2007 and 2012.
# 
# Author: Lucas Venezian Povoa
####################################################################################

setwd("/home/lucas/workspace/BPD4CD/analyzes/")

require("ggplot2")

data <- read.table("data/bibliograph.dat", sep = " ", header = T, dec = ".")

plot <- ggplot(data, aes(
			x    = Year, 
			y    = Number, 
			fill = Database)) + 
		geom_bar(position = "dodge", stat = "identity", colour = "black", alpha = .6) +
		xlab("Ano") + ylab("Número de publicações") +
		scale_fill_hue("Indexadores") +
		theme(
			axis.text    = element_text(size=10, colour = "black"), 
			axis.title   = element_text(size=10, colour = "black"),
			legend.title = element_text(size=10),
			legend.text  = element_text(size=10))

ggsave(filename = "data/bibliograph.png", plot, units = "cm", width = 16, height = 6.2)

