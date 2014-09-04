###
#
#
#

require("ggplot2");
require("scales");

instances = c("c1.xlarge", "m2.4xlarge", "hs1.8xlarge");

gain = c(.1467, .15234, .21543);

data <- data.frame(instance = instances, gain = gain);

plot <- ggplot(data) + 
		geom_bar(stat = "identity", aes(instance, gain, fill = instance), alpha = .6, colour = "black") +
		theme(
			text = element_text(size = 12),
			axis.text.x = element_blank(),
			axis.ticks.x = element_blank()) +
		scale_y_continuous(labels = percent_format()) +
		scale_fill_hue("Instância \nda nuvem\n") +
		xlab("Instância da nuvem") + ylab("Ganho de desempenho\n");

ggsave("/home/lucas/06.17.png", plot, units = "cm", width = 13, height = 6.5);