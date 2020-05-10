rm(list = ls());

library(ggplot2)

data <- read.csv('data.csv');

itrToPlot = c(1*(2**8)-1, 64*(2**8)-1, 128*(2**8)-1, 192*(2**8)-1, 256*(2**8)-1)

subdata001 = data[data$agent=="test 22/100"
    & data$itr %in% itrToPlot,]

mylabeller <- as_labeller(c(
    `1` = "Lever #1",
    `2` = "Lever #2",
    `3` = "Lever #3",
    `4` = "Lever #4"))

fig001 = ggplot(data= subdata001 , aes(x=itr, y=prob))
fig001 = fig001 + geom_line(colour='gray', size=2)
fig001 = fig001 + geom_point(colour='black', size=4, shape=16)
fig001 = fig001 + facet_wrap(~lever, labeller = mylabeller)
fig001 = fig001 + labs(x='Iterations over train'
    , y='Probability of lever selection by agent')
ggsave("fig001.png", plot = fig001)
print(fig001)


subdata002 = data[data$itr %in% itrToPlot,]

fig002 = ggplot(data = subdata002, aes(x=as.factor(itr), y = prob))
fig002 = fig002 + geom_boxplot()
fig002 = fig002 + facet_wrap(~lever, labeller = mylabeller)
fig002 = fig002 + labs(x='Iterations over train'
    , y='Probability of lever selection by agent')
ggsave("fig002.png", plot = fig002)
print(fig002)
