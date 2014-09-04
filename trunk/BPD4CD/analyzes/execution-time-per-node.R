###
#
# Script employed for generating a representation of the activities' 
# execution time used in the location selection framework.
#

require("ggplot2");

node <- c("as1", "loop", "as2", "par", "a1", "a2", "if1", "as3", "a3", "if2", "as4", "as5");

time <- c(3, 2, 3, 1, 7, 6, 1, 3, 60, 1, 3, 3);

plot <- ggplot(data.frame(node = node, time = time)) + 
	    geom_bar(stat = "identity", aes(node, time), alpha = .6, colour = "black") +
	    xlab("Atividade") + ylab("Tempo de execução \nmédio aproximado (s)") +
		theme(text = element_text(size = 12));

ggsave("sparse-matrix-p.png", plot, units = "cm", width = 16, height = 8);
